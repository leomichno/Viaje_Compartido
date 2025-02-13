
import { zodResolver } from "@hookform/resolvers/zod";
import { SubmitHandler, useForm } from "react-hook-form";
import {z} from "zod";
import { InputForm } from "../features/Components";
import { UseFetch } from "../../hooks/UseFetch";


const schema =z.object({
    origen : z.string().min(1,"Debe ingresar un origen"),
    destino : z.string().min(1,"debe ingresar un destino"),
    costoTotal : z.coerce.number().min(1,"Debe ingresar costo de viaje"),
    fechaDeViaje: z.string()
          .regex(/^(\d{2})\/(\d{2})\/(\d{4})$/, "Formato invalido. Use DD/MM/YYYY")
          .transform((fecha) => {
            const [dia, mes, anio] = fecha.split("/");
            return `${anio}-${mes}-${dia}`;
          }),   
})

type FormValue = z.infer<typeof schema>

export const TripForm = () => {
    
    const {request} = UseFetch();


    const {control,handleSubmit,formState:{errors}} = useForm<FormValue>({
        resolver:zodResolver(schema),
        defaultValues:{
            origen:"",
            destino:"",
            costoTotal:0,
            fechaDeViaje:"",
        }
    })

    const onSubmit:SubmitHandler<FormValue> = async (data) =>{

        const token = localStorage.getItem("authToken");

        const options = {
            method:"POST",
            headers:{
            "Authorization":`Bearer ${token}`,
            "Content-type":"application/json"},
            body:JSON.stringify(data)
        }
        try{
        const response = await request("http://localhost:8081/trip",options);
        console.log(response)
        }
        catch(err){
            console.error(err)
        }
    }

    return(
        <div className="form-container">
            <form onSubmit={handleSubmit(onSubmit)}>
                <InputForm name="origen" control={control} label="Origen" type="text" error={errors.origen}/>
                <InputForm name="destino" control={control} label="Destino" type="text" error={errors.destino}/>
                <InputForm name="costoTotal" control={control} label="Costo del viaje" type="text" error={errors.costoTotal} />
                <InputForm name="fechaDeViaje" control={control} label="Fecha de viaje (DD/MM/YYYY)" type="text" error={errors.fechaDeViaje}/>
                <button type="submit">Enviar</button>
            </form>
        </div>
    )


}


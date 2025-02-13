import { zodResolver } from "@hookform/resolvers/zod";
import { SubmitHandler, useForm } from "react-hook-form";
import {  z } from "zod";
import {InputForm} from "../Components/InputForm"
import {UseFetch} from "../../../hooks/UseFetch"

const schema = z.object({
    descripcion: z.string().min(5,"Debe tener una descripcion de al menos 5 letras"),
    capacidadDePasajeros:z.string().min(1,"Proporcionar la capacidad maxima"),
    anoDeFabricacion: z.string()
          .regex(/^(\d{2})\/(\d{2})\/(\d{4})$/, "Formato invalido. Use DD/MM/YYYY")
          .transform((fecha) => {
            const [dia, mes, anio] = fecha.split("/");
            return `${anio}-${mes}-${dia}`;
          }),
    valorDeMercado:z.string().min(5,"Ingresar el valor del auto")
})

type FormValue = z.infer<typeof schema>


export const CarForm = () =>{

    const {request} = UseFetch();

    const {control,handleSubmit,formState:{errors}} = useForm<FormValue>
    ({  resolver:zodResolver(schema),
        defaultValues:{
            descripcion:"",
            capacidadDePasajeros:"",
            anoDeFabricacion:"",
            valorDeMercado:""
        }
    });



    const onSubmit: SubmitHandler<FormValue> = async(data) =>{

        const parsedData = {
            ...data,
            capacidadDePasajeros:Number(data.capacidadDePasajeros),
            valorDeMercado:Number(data.valorDeMercado),

        }

        const token=localStorage.getItem("authToken");
        const options={
            method:"POST",
            headers:{
                "Authorization": `Bearer ${token}`,
                'Content-type':'application/json'},
            body: JSON.stringify(parsedData)
        }

        try{
            const response = await request('http://localhost:8081/car/add',options);
            console.log(response)
        }catch(err){
            console.error(err)
        }
       


    }

    return(
        <div className="form-container">
            <form onSubmit={handleSubmit(onSubmit)}>
                <InputForm name="descripcion" control={control} label="Descripcion" type="text" error={errors.descripcion}/>
                <InputForm name="capacidadDePasajeros" control={control} label="Capacidad De Pasajeros" type="number" error={errors.capacidadDePasajeros}/>
                <InputForm name="anoDeFabricacion" control={control} label="Fecha de fabricacion (DD/MM/YYYY)" type="text" error={errors.anoDeFabricacion}/>
                <InputForm name="valorDeMercado" control={control} label="Valor De Mercado" error={errors.valorDeMercado}/> 
                <button type="submit">Enviar</button>
            </form>
        </div>
    )
}

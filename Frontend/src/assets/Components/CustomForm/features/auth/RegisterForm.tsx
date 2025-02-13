import { zodResolver } from "@hookform/resolvers/zod";
import { SubmitHandler, useForm } from "react-hook-form";
import {InputForm} from "../Components/InputForm"
import { z } from "zod";
import { UseFetch } from "../../../hooks/UseFetch";


// Definición del esquema de validación usando Zod
const schema = z.object({
    nombre: z.string().min(1, "El nombre es obligatorio"),
    email: z.string().email("Correo invalido").min(1, "El correo es obligatorio"),
    username: z.string().min(4, "El usuario debe tener por lo menos 4 caracteres"),
    password: z.string().min(6, "La contraseña debe tener al menos 6 caracteres"),
    confirmarPassword: z.string().min(6, "La confirmación debe tener al menos 6 caracteres"),
    tipoUsuario: z.enum(["PASAJERO", "CONDUCTOR"], { message: "Debe elegir un rol" })
}).refine(data => data.password === data.confirmarPassword, {
    message: "Las contraseñas deben coincidir",
    path: ['confirmarPassword']
});

// creamos el tipo formvalue a partir del esquema que lo generamos con z.infer
// tenemos que tener en cuenta que es lo mismo que decir lo siguiente
// type FormValue ={nombre:string,email:string..... y asi} pero en su lugar esto lo genera automaticamente a partir del esquema realizado
type FormValue = z.infer<typeof schema>;

const RegisterForm = () => {

    const {request,error} = UseFetch();

    const { control, handleSubmit, formState: { errors },register } = useForm<FormValue>({
        resolver: zodResolver(schema),
        defaultValues: {
            nombre: '',
            email: '',
            username: '',
            password: '',
            confirmarPassword: '',
            tipoUsuario: 'PASAJERO'
        }
    });

    const onSubmit: SubmitHandler<FormValue> = async(data) => {
        const option = {
            method: 'POST',
            headers:{'Content-Type' : 'application/json'},
            body: JSON.stringify(data)
        }
        try{
            const response = await request('http://localhost:8081/auth/register',option);
            console.log("usuario creado",response);
        }catch(err){
            console.error("Error al crear el usuario: ",err)
            console.log(error)
        }
    };

    return (
        <div className="form-container">
            <form onSubmit={handleSubmit(onSubmit)}>
                <InputForm name="nombre" control={control} label="Nombre" type="text" error={errors.nombre} />
                <InputForm name="email" control={control} label="Email" type="email" error={errors.email} />
                <InputForm name="username" control={control} label="Usuario" type="text" error={errors.username} />
                <InputForm name="password" control={control} label="Contraseña" type="password" error={errors.password} />
                <InputForm name="confirmarPassword" control={control} label="Confirmar Contraseña" type="password" error={errors.confirmarPassword} />

                <div className="select">
                    <select {...register("tipoUsuario")}>
                        <option value="PASAJERO">PASAJERO</option>
                        <option value="CONDUCTOR">CONDUCTOR</option>
                    </select>
                    {errors.tipoUsuario && <p style={{ color: "red" }}>{errors.tipoUsuario.message}</p>}
                </div>

                <button type="submit">Enviar</button>
            </form>
        </div>
    );
};


export default RegisterForm;
import { zodResolver } from "@hookform/resolvers/zod";
import { SubmitHandler, useForm } from "react-hook-form";
import {InputForm} from "../Components/InputForm"
import { z } from "zod";
import { UseFetch } from "../../../hooks/UseFetch";
import AuthContext from "../../../CustomForm/features/auth/AuthProvider"
import { useContext } from "react";
import "./Styles/Forms.css"


// Definición del esquema de validación usando Zod
const schema = z.object({
    username: z.string().min(4, "El usuario debe tener por lo menos 4 caracteres"),
    password: z.string().min(6, "La contraseña debe tener al menos 6 caracteres")
});

// Inferir el tipo del formulario a partir del esquema
type FormValue = z.infer<typeof schema>;

const LoginForm = () => {

    const {request,loading,error} = UseFetch();
    const {login } = useContext(AuthContext);

    const { control, handleSubmit, formState: { errors } } = useForm<FormValue>({
        resolver: zodResolver(schema),
        defaultValues: {
            username: '',
            password: ''
        }
    });

    const onSubmit: SubmitHandler<FormValue> = async(data) => {
        const option = {
            method: 'POST',
            headers:{'Content-Type' : 'application/json'},
            body: JSON.stringify(data)
        }
        try{
            const response = await request('http://localhost:8081/auth/login',option);
            console.log([...response.headers.entries()]);
            console.log(response);
            console.log(response.headers.get)
            if (response.ok) {
                const token = response.headers.get("authorization");
                if (token) {
                    login(token);
                } else {
                    console.error("Token no recibido");
                }
            } else {
                console.error("Error en la autenticación:", response.status, response.statusText);
            }
        }catch(err){
            console.error("Error el usuario no se encontro: ",err)
            console.log(error)
        }
    };

    return (
        <div className="form-container">
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="input-group">
                    <InputForm name="username" control={control} label="Usuario" type="text" error={errors.username} />
                </div>
                <div className="input-group">
                    <InputForm name="password" control={control} label="Contraseña" type="password" error={errors.password} />
                </div>
                <button type="submit" className={loading ? "loading" : ""} disabled={loading}>
                    {loading ? "Cargando..." : "Enviar"}
                </button>
            </form>
        </div>
    );
};


export default LoginForm;
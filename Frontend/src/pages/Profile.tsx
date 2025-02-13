import { useEffect, useState } from "react";
import { UseFetch } from "../assets/Components/hooks/UseFetch";
import "../assets/Components/styles/Profile.css";

const URL = "http://localhost:8081/user";

type Perfil = {
    nombre: string;
    email: string;
    tipoUsuario: string;
    saldo: number;
};

const Profile = () => {
    const { request, loading, error } = UseFetch();
    const [perfil, setPerfil] = useState<Perfil|null>(null); // Cambiado a null

    useEffect(() => {
        const token = localStorage.getItem("authToken");
        const options = {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-type": "application/json",
            },
        };

        const mostarPerfil = async () => {
            try {
                const response = await request(URL, options);
                if (!response.ok) {
                    throw new Error("No se pudo encontrar el perfil");
                }
                const data = await response.json();
                console.log(data);
                setPerfil(data);
            } catch (err) {
                console.error(err);
            }
        };
        mostarPerfil();
    }, []);

    if (loading) return <p>Cargando...</p>;
    if (error) return <p>Error: {error.message}</p>;
    if (!perfil) return <p>No se encontró perfil.</p>; // Nueva validación

    return (
        <div className="profile-container">
            <div className="profile-card">
                <p>Nombre: {perfil.nombre}</p>
                <p>Email: {perfil.email}</p>
                <p>Tipo: {perfil.tipoUsuario}</p>
                <h2 className="balance">${perfil.saldo}</h2>
            </div>
        </div>
    );
};

export default Profile;
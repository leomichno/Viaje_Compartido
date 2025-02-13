import { useEffect, useState } from "react";
import { UseFetch } from "../hooks/UseFetch";
import "./Styles/AvailableTrips.css"
import { Button } from "../Button/Button";

const URL = "http://localhost:8081/trip/trips";

const URL1 = "http://localhost:8081/pasajero/unirse"

interface Viaje {
    id: number;
    origen: string;
    destino: string;
    costoTotal: number;
}

export const AvailableTrips = () => {
    const [viajes, setViajes] = useState<Viaje[]>([]);
    const { request, loading, error } = UseFetch();
    const [errorViajes, setErrorViajes] = useState<{ [key: number]: string }>({});


    useEffect(() => {
        const fetchTrip = async () => {
            const token = localStorage.getItem("authToken");

            const options = {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-type": "application/json",
                },
            };

            try {
                const response = await request(URL, options);
                if (!response.ok) {
                    throw new Error("No se obtuvo la lista de viajes");
                }
                const data = await response.json();
                setViajes(data);
            } catch (err) {
                console.error(err);
            }
        };
        fetchTrip();
    }, []);


    const respuesta = async(id:number) =>{
        const token = localStorage.getItem("authToken");

        const options = {
            method:"POST",
            headers:{
            "Authorization":`Bearer ${token}`,
            "Content-type":"application/json"},
            body:JSON.stringify(id)
        }

        setErrorViajes(prevErrors => ({ ...prevErrors, [id]: "" }));


        try {
            const response = await request(URL1, options);
            const data = await response.json();
    
            if (!data.success) {
                throw new Error(data.message);
            }


            setErrorViajes(prevErrors => ({ ...prevErrors, [id]: "" }));
        } catch (err) {
    if (err instanceof Error) {
        console.error(err.message);
    } else {
        console.error("Error desconocido", err);
    }
}};




    return (
        <div className="trips-container">
            <h1 className="trips-title">Viajes Disponibles</h1>

            {loading && <p className="trips-loading">Cargando viajes...</p>}
            {error && <p className="trips-error">Error al cargar viajes</p>}
            {viajes.length === 0 && !loading && <p className="trips-empty">No hay viajes disponibles</p>}

            <div className="trips-grid">
                {viajes.map((viaje) => (
                    <div key={viaje.id} className="trip-card">
                        <p className="trip-origin">Origen: {viaje.origen}</p>
                        <p className="trip-destination">Destino: {viaje.destino}</p>
                        <p className="trip-cost">Costo: ${viaje.costoTotal}</p>
                        <Button label="Unirme" ParentMethod={() => respuesta(viaje.id)}/>
                        {errorViajes[viaje.id] && <p className="trip-error">{errorViajes[viaje.id]}</p>}
                    </div>
                ))}
            </div>
        </div>
    );
};
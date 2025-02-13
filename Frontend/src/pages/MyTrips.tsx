import { useEffect, useState } from "react";
import { UseFetch } from "../assets/Components/hooks/UseFetch";
const URL ='http://localhost:8081/trip/trips/user'

interface Trip {
    id: number;
    origen: string;
    destino: string;
    costoTotal: number;
}

const MyTrips = () =>{

    const {request,loading,error} = UseFetch();
    const [viajes,setViajes] = useState<Trip[]>([])


    useEffect(()=>{
        const token = localStorage.getItem("authToken");
        const options ={
            method:"GET",
            headers:{
                "Authorization": `Bearer ${token}`,
                'Content-type':'application/json'},
        }

        const mostarViajes = async() =>{
            try{
                const response = await request(URL,options);
                console.log(response);
                if(!response){throw new Error("no pudimos traer tus viajes")}
                const data = await response.json();
                console.log(data);
                setViajes(data);
            }catch(err){
                console.error(err);
            }
        }
        mostarViajes()
    },[]
)

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
                        </div>
                    ))}
                </div>
            </div>
        );
}


export default MyTrips;
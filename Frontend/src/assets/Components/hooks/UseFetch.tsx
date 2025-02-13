import { useState } from "react";

export const UseFetch = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<Error | null>(null);

    
    const request = async (url: string, options: RequestInit = {}) => {
        setLoading(true);
        setError(null);
        try {
            const response = await fetch(url, options);
            
        
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            setLoading(false);
            return response;
        } catch (err) {
         
            if (err instanceof Error) {
                setError(err);
            } else {
                setError(new Error("Ocurrió un error desconocido"));
            }
            setLoading(false);
            throw err; 
        }
    };

    return { request, loading, error };
};

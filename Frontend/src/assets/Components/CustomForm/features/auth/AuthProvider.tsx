import { jwtDecode } from "jwt-decode";
import { createContext, ReactNode, useEffect, useState } from "react";


const AuthContext = createContext<any>(null);

interface AuthProviderProps {
    children: ReactNode; 
}


export const AuthProvider = ({ children }: AuthProviderProps) => {
    const [token, setToken] = useState<string | null>(() => localStorage.getItem("authToken"));
    const [role, setRole] = useState<string | null>(null);

    useEffect(() => {
        if (token) {
            try {
                const decoded: any = jwtDecode(token);
                console.log("el rol es : " +decoded.role)
                setRole(decoded.role);
            } catch (error) {
                console.error("Error en la decodificación del token", error);
                setToken(null);
                setRole(null);
                localStorage.removeItem("authToken");
            }
        }
    }, [token]);

    const login = (newToken: string) => {
        setToken(newToken);
        localStorage.setItem("authToken", newToken);
    };

    const logout = () => {
        setToken(null);
        setRole(null);
        localStorage.removeItem("authToken");
    };

    return (
        <AuthContext.Provider value={{role,logout,login}}>
            {children}
        </AuthContext.Provider>
    );
};


export default AuthContext;
import { useContext, useEffect, useState } from "react";
import AuthContext from "./assets/Components/CustomForm/features/auth/AuthProvider";
import Menu from "./assets/Components/Menu/Menu";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./assets/Components/CustomForm/features/auth/LoginForm";
import RegisterPage from "./assets/Components/CustomForm/features/auth/RegisterForm";
import ProfilePage from "./pages/Profile";
import MyTripsPage from "./pages/MyTrips";
import { AvailableTrips } from "./assets/Components/Passengers/AvailableTrips";
import { CarForm } from "./assets/Components/CustomForm/features/cars";
import { TripForm } from "./assets/Components/CustomForm/trips/TripForm";
import "./App.css"

// Definimos los tipos de role
type Role = "" | "PASAJERO" | "CONDUCTOR" | null;

const App = () => {
  const authContext = useContext(AuthContext);

  if (!authContext) {
    throw new Error("AuthContext must be used within an AuthProvider");
  }

  const { role, logout } = authContext;
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  useEffect(() => {
    setIsAuthenticated(!!role); // Si hay un rol, significa que el usuario está autenticado
  }, [role]);

  // Aseguramos que el valor de role sea uno de los valores esperados.
  const normalizedRole: Role = role === "PASAJERO" || role === "CONDUCTOR" ? role : "";

  return (
    <Router>
      <div>
        <Menu isAuthenticated={isAuthenticated} role={normalizedRole} onLogout={logout} />
      </div>
      
      <div className="cont-pages">
        <Routes>
          {/* Rutas públicas */}
          {!isAuthenticated ? (
            <>
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="*" element={<Navigate to="/login" replace />} />
            </>
          ) : normalizedRole === "PASAJERO" ? (
            // Rutas para pasajeros
            <>
              <Route path="/join-trip" element={<AvailableTrips />} />
              <Route path="/my-trips" element={<MyTripsPage />} />
              <Route path="/profile" element={<ProfilePage />} />
              <Route path="*" element={<Navigate to="/join-trip" replace />} />
            </>
          ) : normalizedRole === "CONDUCTOR" ? (
            // Rutas para conductores
            <>
              <Route path="/create-trip" element={<TripForm />} />
              <Route path="/my-trips" element={<MyTripsPage />} />
              <Route path="/profile" element={<ProfilePage />} />
              <Route path="/my-car" element={<CarForm />} />
              <Route path="*" element={<Navigate to="/profile" replace />} />
            </>
          ) : null}
        </Routes>
      </div>
    </Router>
  );
};

export default App;

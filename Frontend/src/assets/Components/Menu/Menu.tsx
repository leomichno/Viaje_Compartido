import { Link } from "react-router-dom";
import "./style/Menu.css";

// Actualizamos la interfaz para permitir null en el tipo de role
interface MenuProps {
  isAuthenticated: boolean;
  role: "PASAJERO" | "CONDUCTOR" | "" | null; // Permitimos null
  onLogout: () => void;
}

const Menu: React.FC<MenuProps> = ({ isAuthenticated, role, onLogout }) => {
  return (
    <nav>
      <ul className="nav-links">
        {!isAuthenticated ? (
          <>
            <li><Link to="/register">Registrarse</Link></li>
            <li><Link to="/login">Iniciar Sesión</Link></li>
          </>
        ) : role === "PASAJERO" ? (
          <>
            <li><Link to="/join-trip">Unirse a Viaje</Link></li>
            <li><Link to="/my-trips">Mis Viajes</Link></li>
            <li><Link to="/profile">Perfil</Link></li>
          </>
        ) : role === "CONDUCTOR" ? (
          <>
            <li><Link to="/create-trip">Crear Viaje</Link></li>
            <li><Link to="/my-trips">Mis Viajes Creados</Link></li>
            <li><Link to="/profile">Perfil</Link></li>
            <li><Link to="/my-car">Automóvil</Link></li>
          </>
        ) : (
          <li><Link to="/profile">Perfil</Link></li> // Aquí agregas un perfil por defecto si no hay rol
        )}
      </ul>

      {isAuthenticated && (
        <button className="logout-button" onClick={onLogout}>Cerrar Sesión</button>
      )}
    </nav>
  );
};

export default Menu;

import {Control,Controller,FieldError} from "react-hook-form";
import "./CustomInput.css"


interface Prop {
    name: string;
    control:Control<any>;
    label:string|number;
    type?:string;
    error?:FieldError;
}

export const InputForm =({name,control,label,type,error}:Prop)=>{
    return(
        <div className="form-group">
            <label htmlFor={name}>{label}</label>
            <Controller
                name={name}
                control={control}
                render={({field})=>
                <input id={name} type={type} {...field} className={`form-control ${error?"is-invalid":" "}`}/>
            }
            />
            {error && <p className="error">{error.message}</p>}
        </div>
    )
}

import "./Styles/Button.css"


interface Props{
    label:string,
    ParentMethod:()=>void
}


export const Button = ({label,ParentMethod}:Props) =>{
        return(
            <button className="custom-button" onClick={ParentMethod}>{label}</button>
        )
}

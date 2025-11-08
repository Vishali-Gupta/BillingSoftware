import './MangeItems.css'
import ItemsList from "../../Components/ItemsList/ItemsList.jsx";
import ItemsForm from "../../Components/ItemsForm/ItemsForm.jsx";
const ManageItems = () => {
    return (
        <div className="items-container text-light">
            <div className="left-column">
                <ItemsForm/>
            </div>

            <div className="right-column">
               <ItemsList/>

            </div>
        </div>
    )
}
export default ManageItems;
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Menubar from "./Components/MenuBar/Menubar.jsx";
import Dashboard from "./Pages/Dashboard/Dashboard.jsx";
import Explore from "./Pages/Explore/Explore.jsx";
import ManageCategory from "./Pages/ManageCategory/ManageCategory.jsx";
import ManageItems from "./Pages/ManageItems/ManageItems.jsx";
import ManageUsers from "./Pages/ManageUsers/ManageUsers.jsx";


const App = () => {
    return(
        <div>
            <Menubar />
            <Routes>
                <Route path="/dashboard" element={<Dashboard/>}/>
                <Route path="/explore" element={<Explore/>}/>
                <Route path="/category" element={<ManageCategory/>}/>
                <Route path="/items" element={<ManageItems/>}/>
                <Route path="/users" element={<ManageUsers/>}/>
                <Route path="/" element={<Dashboard/>}/>
            </Routes>

        </div>
    );

}
export default App;
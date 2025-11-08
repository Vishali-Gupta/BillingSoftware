import { createContext, useEffect, useState } from "react";
import { fetchCategories } from "../Service/CategoryService.js";

export const AppContext = createContext(null);

export const AppContextProvider = (props) => {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        async function loadData() {
            try {
                const response = await fetchCategories();

                // Adjust this line depending on your API response
                setCategories(response.data || response);
            } catch (error) {
                console.error("Error fetching categories:", error);
            }
        }
        loadData();
    }, []);

    const contextValue = { categories, setCategories };

    return (
        <AppContext.Provider value={contextValue}>
            {props.children}
        </AppContext.Provider>
    );
};

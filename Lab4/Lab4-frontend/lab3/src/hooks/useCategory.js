import {useEffect, useState} from "react";
import categoryRepository from "../repository/categoryRepository.js";

const useCategory = () => {
    const [category,setCategory] = useState([]);

    useEffect(()=>{
        categoryRepository
            .findAll()
            .then((response) =>{
                setCategory(response.data);
            })
            .catch((error) => console.log(error))
    },[])
    return category;
}

export default useCategory;
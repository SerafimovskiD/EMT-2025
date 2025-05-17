import { useCallback, useEffect, useState } from "react";
import countryRepository from "../repository/countryRepository.js";

const initialState = {
    countries: [],
    loading: true,
};

const useCountry = () => {
    const [state, setState] = useState(initialState);

    const fetchCountry = useCallback(() => {
        countryRepository
            .findAll()
            .then((response) => {
                setState({
                    countries: response.data,
                    loading: false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        countryRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a country.");
                fetchCountry();
            })
            .catch((error) => console.log(error));
    }, [fetchCountry]);

    const onEdit = useCallback((id, data) => {
        countryRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited country with ID ${id}.`);
                fetchCountry();
            })
            .catch((error) => console.log(error));
    }, [fetchCountry]);

    const onDelete = useCallback((id) => {
        countryRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted country with ID ${id}.`);
                fetchCountry();
            })
            .catch((error) => console.log(error));
    }, [fetchCountry]);

    useEffect(() => {
        fetchCountry();
    }, [fetchCountry]);

    return { ...state, onAdd, onEdit, onDelete };
};

export default useCountry;

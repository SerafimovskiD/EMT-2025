import { useEffect, useState } from "react";
import hostRepository from "../repository/hostRepository.js";

const useHostDetails = (id) => {
    const [state, setState] = useState({
        host: null,
        country: null,
    });

    useEffect(() => {
        if (!id) return;

        hostRepository
            .findById(id)
            .then((response) => {
                console.log(id);
                console.log("Response data:", response.data);

                const host = {
                    id: response.data.id,
                    name: response.data.name,
                    surname: response.data.surname,
                    countryId: response.data.country
                };

                const country = response.data.country ?? null;

                setState({ host, country });
            })
            .catch((error) => {
                console.log("Error fetching host or country:", error);
            });
    }, [id]);

    return state;
};

export default useHostDetails;

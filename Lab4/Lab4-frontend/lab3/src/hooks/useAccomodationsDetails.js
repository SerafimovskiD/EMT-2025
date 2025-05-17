import { useEffect, useState } from "react";
import accommodationRepository from "../repository/accommodationRepository.js";
import hostRepository from "../repository/hostRepository.js"; // Треба да имаш овој repository

const useAccommodationsDetails = (id) => {
    const [state, setState] = useState({
        accommodation: null,
    });

    useEffect(() => {
        if (id) {
            accommodationRepository
                .findById(id)
                .then(async (response) => {
                    const accommodation = response.data;

                    if (accommodation.host !== undefined && accommodation.host !== null) {
                        try {
                            const hostResponse = await hostRepository.findById(accommodation.host);
                            accommodation.host = hostResponse.data;
                        } catch (error) {
                            console.error("Failed to fetch host details:", error);
                            accommodation.host = null; // Да не крши ако не успее
                        }
                    }

                    setState((prevState) => ({
                        ...prevState,
                        accommodation,
                    }));
                })
                .catch((error) => {
                    console.log("Error fetching accommodation:", error);
                });
        }
    }, [id]);

    return state;
};

export default useAccommodationsDetails;

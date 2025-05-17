import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import useCountries from "../../../hooks/useCountry.js";
import AddCountryDialog from "../../components/country/AddCountryDialog/AddCountryDialog.jsx";
import CountriesGrid from "../../components/country/CountryGrid/CountryGrid.jsx";

const CountryPage = () => {
    const {countries, loading, onAdd, onEdit, onDelete} = useCountries();
    const [addCountriesDialogOpen, setAddCountriesDialogOpen] = useState(false);

    return (
        <>
            <Box className="countries-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddCountriesDialogOpen(true)}>
                                Add Country
                            </Button>
                        </Box>
                        <CountriesGrid countries={countries} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddCountryDialog
                open={addCountriesDialogOpen}
                onClose={() => setAddCountriesDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default CountryPage;
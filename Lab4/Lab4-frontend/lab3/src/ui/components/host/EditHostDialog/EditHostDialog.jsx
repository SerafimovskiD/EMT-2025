import React, {useState, useEffect} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useCountries from "../../../../hooks/useCountries.js";
const EditHostDialog = ({open, onClose, host, onEdit}) => {
    const countries = useCountries();
    const [formData, setFormData] = useState({
        "name": host.name,
        "surname": host.surname,
        "country": host.country,
    });
    useEffect(() => {
        if (host) {
            setFormData({
                name: host.name || "",
                surname: host.surname || "",
                country: host.country || "",
            });
        }
    }, [host]);
    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({
            ...formData,
            [name]: name === "country" ? Number(value) : value
        });
    };

    const handleSubmit = () => {
        onEdit(host.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Host</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    name="surname"
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        name="country"
                        value={formData.country}
                        onChange={handleChange}
                        label="Country"
                        variant="outlined"
                    >
                        {countries.map((country) => (
                        <MenuItem key={country.id} value={country.id}>
                            {country.name}
                        </MenuItem>))}

                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditHostDialog;
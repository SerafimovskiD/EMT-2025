    import React, { useState } from 'react';
    import {
        Button,
        Dialog,
        DialogActions,
        DialogContent,
        DialogTitle,
        TextField,
        FormControl,
        InputLabel,
        Select,
        MenuItem
    } from "@mui/material";

    const categories = [
        "ROOM",
        "HOUSE",
        "FLAT",
        "APARTMENT",
        "HOTEL",
    ];

    const AddAccommodationDialog = ({ open, onClose, onAdd, hosts=[] }) => {
        const initialFormData = {
            name: "",
            category: "",
            host: "",
            numRooms: ""
        };

        const [formData, setFormData] = useState(initialFormData);

        const handleChange = (event) => {
            const { name, value } = event.target;
            setFormData(prev => ({
                ...prev,
                [name]: name === "numRooms" ? parseInt(value, 10) : value
            }));
        };

        const handleSubmit = () => {
            onAdd({
                name: formData.name,
                category: formData.category,
                host: parseInt(formData.host, 10),
                numRooms: formData.numRooms
            });
            setFormData(initialFormData);
            onClose();
        };

        return (
            <Dialog open={open} onClose={onClose}>
                <DialogTitle>Add Accommodation</DialogTitle>
                <DialogContent>
                    <TextField
                        margin="dense"
                        label="Name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        fullWidth
                    />
                    <FormControl fullWidth margin="dense">
                        <InputLabel>Category</InputLabel>
                        <Select
                            label="Category"
                            name="category"
                            value={formData.category}
                            onChange={handleChange}
                        >
                            {categories.map(cat => (
                                <MenuItem key={cat} value={cat}>{cat}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <FormControl fullWidth margin="dense">
                        <InputLabel>Host</InputLabel>
                        <Select
                            label="Host"
                            name="host"
                            value={formData.host}
                            onChange={handleChange}
                        >
                            {hosts.map(h => (
                                <MenuItem key={h.id} value={h.id}>
                                    {h.name} {h.surname}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <TextField
                        margin="dense"
                        label="Number of Rooms"
                        name="numRooms"
                        type="number"
                        value={formData.numRooms}
                        onChange={handleChange}
                        fullWidth
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button
                        onClick={handleSubmit}
                        variant="contained"
                        color="primary"
                        disabled={!formData.name || !formData.category || !formData.host || !formData.numRooms}
                    >
                        Add
                    </Button>
                </DialogActions>
            </Dialog>
        );
    };

    export default AddAccommodationDialog;

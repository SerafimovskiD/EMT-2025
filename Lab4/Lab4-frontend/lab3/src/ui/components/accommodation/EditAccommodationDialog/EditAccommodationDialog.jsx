import React, { useState, useEffect } from 'react';
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
import useHosts from "../../../../hooks/useHost.js";
import useCategory from "../../../../hooks/useCategory.js";

// const categories = [
//     "ROOM",
//     "HOUSE",
//     "FLAT",
//     "APARTMENT",
//     "HOTEL"
// ];

const EditAccommodationDialog = ({ open, onClose, onEdit, accommodation }) => {
    const categories=useCategory();

    const [formData, setFormData] = useState({
        name: "",
        category: "",
        host: "",
        numRooms: ""
    });

    // const { hosts=[] } = useHosts();
    const hosts=useHosts();
    useEffect(() => {
        if (accommodation) {
            setFormData({
                name: accommodation.name || "",
                category: accommodation.category || "",
                host: accommodation.host || "",  // we store just the host id
                numRooms: accommodation.numRooms || ""
            });
        }
    }, [accommodation]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = () => {
        const updatedAccommodation = {
            ...formData,
            numRooms: parseInt(formData.numRooms),
        };
        onEdit(accommodation.id, updatedAccommodation);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Accommodation</DialogTitle>
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
                        {hosts.map(host => (
                            <MenuItem key={host.id} value={host.id}>
                                {host.name} {host.surname}
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
                    disabled={!formData.name || !formData.category || !formData.host}
                >
                    Save
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAccommodationDialog;

import React, { useState } from 'react';
import { Box, Button, CircularProgress } from "@mui/material";
import useAccommodations from "../../../hooks/useAccommodations.js";
import AddAccommodationDialog from "../../components/accommodation/AddAccommodationDialog/AddAccommodationDialog.jsx";
import AccommodationsGrid from "../../components/accommodation/AccommodationGrid/AccommodationGrid.jsx";
import useHosts from "../../../hooks/useHosts.js";

const AccommodationPage = () => {
    const { accommodations, loading, onAdd, onEdit, onDelete } = useAccommodations();
    const [addAccommodationDialogOpen, setAddAccommodationDialogOpen] = useState(false);
    const { hosts } = useHosts();

    return (
        <>
            <Box className="accommodations-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress />
                    </Box>
                )}
                {!loading && (
                    <>
                        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: 2 }}>
                            <Button variant="contained" color="primary" onClick={() => setAddAccommodationDialogOpen(true)}>
                                Add Accommodation
                            </Button>
                        </Box>
                        <AccommodationsGrid accommodations={accommodations} onEdit={onEdit} onDelete={onDelete} />
                    </>
                )}
            </Box>
            <AddAccommodationDialog
                open={addAccommodationDialogOpen}
                onClose={() => setAddAccommodationDialogOpen(false)}
                hosts={hosts}
                onAdd={onAdd}
            />
        </>
    );
};

export default AccommodationPage;

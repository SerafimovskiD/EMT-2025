import { useState } from "react";
import {
    Card,
    CardContent,
    Typography,
    CardActions,
    Button,
    Box
} from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import { useNavigate } from "react-router";
import EditAccommodationDialog from "../EditAccommodationDialog/EditAccommodationDialog.jsx";
import DeleteAccommodationDialog from "../DeleteAccommodationDialog/DeleteAccommodationDialog.jsx";

const AccommodationCard = ({ accommodation, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1 }}>
                <CardContent>
                    <Typography variant="h5">{accommodation.name}</Typography>
                    <Typography variant="subtitle1">
                        Category: {accommodation.category}
                    </Typography>
                    <Typography variant="subtitle2">
                        Host: {accommodation.host?.name} {accommodation.host?.surname}
                    </Typography>
                    <Typography variant="subtitle2">
                        Rooms: {accommodation.numRooms}
                    </Typography>
                </CardContent>
                <CardActions sx={{ justifyContent: "space-between" }}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon />}
                        onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon />}
                            sx={{ mr: "0.25rem" }}
                            onClick={() => setEditDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon />}
                            onClick={() => setDeleteDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            <EditAccommodationDialog
                open={editDialogOpen}
                onClose={() => setEditDialogOpen(false)}
                accommodation={accommodation}
                onEdit={onEdit}
            />

            <DeleteAccommodationDialog
                open={deleteDialogOpen}
                onClose={() => setDeleteDialogOpen(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />
        </>
    );
};

export default AccommodationCard;

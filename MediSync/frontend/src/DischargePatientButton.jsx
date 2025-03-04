import React from "react";
import { Button } from "@mui/material";
export function DischargePatientButton() {
  return (
    <Button
      variant="contained" disableRipple disableFocusRipple
      sx={{
        backgroundColor: "#CC6963",
        color: "#eee",
        width: "100%",
        height: 50,
        width: "100%",
        fontFamily: 'Montserrat, sans-serif',
        fontWeight: "bold",
        fontSize: 20,
        borderRadius: "4px",
        padding: "10px",
        outline: "none !important",
      }}
    >
      Discharge Patient
    </Button>
  );
}
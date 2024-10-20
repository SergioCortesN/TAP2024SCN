package components;

import com.example.tap2024scn.models.CancionDAO;
import com.example.tap2024scn.vistas.FormCanciones;
import com.example.tap2024scn.vistas.FormCliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCellCanciones extends TableCell<CancionDAO, String>
{
    Button btnCelda;

    public ButtonCellCanciones(String str)
    {
        btnCelda = new Button(str);
        btnCelda.setOnAction(event -> EventoBoton(str));
    }

    private void EventoBoton(String str)
    {
        CancionDAO objCan = this.getTableView().getItems().get(this.getIndex());
        if(str.equals("Editar"))
        {
            new FormCanciones(this.getTableView(), objCan);
            this.getTableView().setItems(objCan.selectAll());
            this.getTableView().refresh();
        }else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Mensaje del Sistema :3");
            alerta.setContentText("¿Deseas eliminar el registro seleccionado?");
            Optional<ButtonType> opcion = alerta.showAndWait();
            if(opcion.get() == ButtonType.OK)
            {
                objCan.delete();
                this.getTableView().setItems(objCan.selectAll());
                this.getTableView().refresh();

            }
        }
    }

    @Override
    protected void updateItem(String s, boolean b)
    {
        super.updateItem(s, b);
        if (!b)
        {
            this.setGraphic(btnCelda);
        }
    }
}

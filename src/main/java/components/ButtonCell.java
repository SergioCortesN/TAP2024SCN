package components;

import com.example.tap2024scn.models.ClienteDAO;
import com.example.tap2024scn.vistas.FormCliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCell extends TableCell<ClienteDAO, String>
{
    Button btnCelda;

    public ButtonCell(String str)
    {
        btnCelda = new Button(str);
        btnCelda.setOnAction(event -> EventoBoton(str));
    }

    private void EventoBoton(String str)
    {
        ClienteDAO objCte = this.getTableView().getItems().get(this.getIndex());
        if(str.equals("Editar"))
        {
            new FormCliente(this.getTableView(), objCte);
            this.getTableView().setItems(objCte.selectAll());
            this.getTableView().refresh();
        }else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Mensaje del Sistema :3");
            alerta.setContentText("¿Deseas eliminar el registro seleccionado?");
            Optional<ButtonType> opcion = alerta.showAndWait();
            if(opcion.get() == ButtonType.OK)
            {
                objCte.delete();
                this.getTableView().setItems(objCte.selectAll());
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

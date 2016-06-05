package ske.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.commands.Command;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class ConfirmDialog extends Dialog {

    @BindView(R.id.img_fingerprint)
    ImageView imgFingerPrint;

    private Command command;

    public ConfirmDialog(Context context) {
        super(context,R.style.dialogStyle);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        if( command == null ) return;
        command.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        ButterKnife.bind(this);
    }

    @OnLongClick(R.id.img_fingerprint)
    public boolean longClick() {
        execute();
        dismiss();
        return true;
    }

    @OnClick(R.id.btn_close)
    public void close() {
        dismiss();
    }
}

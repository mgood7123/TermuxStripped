package com.termux.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.View;

import androidx.annotation.Nullable;

import com.termux.terminal.TerminalSession;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ACTIVITY extends Activity {
    Termux TERMUX = new Termux(this);

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        TERMUX.initTerm();
    }


    final List<TerminalSession> mTerminalSessions = new ArrayList<>();

    final List<BackgroundJob> mBackgroundTasks = new ArrayList<>();

    TerminalSession createTermSession() {
        TerminalSession session = new TerminalSession("/bin/cat", "/", new String[]{"/bin/cat"}, new String[]{""});
        mTerminalSessions.add(session);

        return session;
    }

    @Override
    protected void onStart() {
        super.onStart();

        TerminalSession newSession = createTermSession();
        newSession.mSessionName = "session";
        // Make the newly created session the current one to be displayed:
        TermuxPreferences.storeCurrentSession(this, newSession);
        TERMUX.switchToSession(newSession);
        TERMUX.mTerminalView.updateSize();
        newSession.write("test");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TERMUX.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TERMUX.onBackPressed();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}

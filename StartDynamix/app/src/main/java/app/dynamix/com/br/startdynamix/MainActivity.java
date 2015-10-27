package app.dynamix.com.br.startdynamix;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.ambientdynamix.api.application.ContextEvent;
import org.ambientdynamix.api.application.ContextPluginInformation;
import org.ambientdynamix.api.application.ContextSupportInfo;
import org.ambientdynamix.api.application.ContextSupportResult;
import org.ambientdynamix.api.application.IDynamixFacade;
import org.ambientdynamix.api.application.IDynamixListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //variables
    private static final String TAG = MainActivity.class.getSimpleName();
    private IDynamixFacade dynamix;
    private ServiceConnection sConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.w(TAG, "onServiceConnected");
            // We're connected, so add ourselves as a Dynamix listener
            try {
                // Create a Dynamix Facade using the incoming IBinder
                dynamix = IDynamixFacade.Stub.asInterface(service);
                // Create a Dynamix listener using the callback
                dynamix.addDynamixListener(dynamixCallback);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // We've been disconnected, so null out our existing IDynamixFacade
            Log.w(TAG, "onServiceDisconnected");
            dynamix = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = new Intent(this ,IDynamixFacade.class);
        //Log.w(TAG, "name: " + IDynamixFacade.class.getSimpleName());
        Log.w(TAG, "bind: " + bindService(intent, sConnection, Context.BIND_AUTO_CREATE));
        //bindService(new Intent(this, IDynamixFacade.class.getName()), sConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private IDynamixListener dynamixCallback = new IDynamixListener.Stub(){
        @Override
        public void onDynamixListenerAdded(String listenerId) throws RemoteException {
        }

        @Override
        public void onDynamixListenerRemoved() throws RemoteException {
        }

        @Override
        public void onAwaitingSecurityAuthorization() throws RemoteException {

        }

        @Override
        public void onSecurityAuthorizationGranted() throws RemoteException {

        }

        @Override
        public void onSecurityAuthorizationRevoked() throws RemoteException {

        }

        @Override
        public void onSessionOpened(String sessionId) throws RemoteException {
            Log.w(TAG, "sessionOpened: " + sessionId);
        }

        @Override
        public void onSessionClosed() throws RemoteException {
        }

        @Override
        public void onContextEvent(ContextEvent contextEvent) throws RemoteException {

        }

        @Override
        public void onContextSupportAdded(ContextSupportInfo contextSupportInfo) throws RemoteException {

        }

        @Override
        public void onContextSupportRemoved(ContextSupportInfo contextSupportInfo) throws RemoteException {

        }

        @Override
        public void onContextTypeNotSupported(String s) throws RemoteException {

        }

        @Override
        public void onInstallingContextSupport(ContextPluginInformation contextPluginInformation, String s) throws RemoteException {

        }

        @Override
        public void onInstallingContextPlugin(ContextPluginInformation contextPluginInformation) throws RemoteException {

        }

        @Override
        public void onContextPluginInstallProgress(ContextPluginInformation contextPluginInformation, int i) throws RemoteException {

        }

        @Override
        public void onContextPluginInstalled(ContextPluginInformation contextPluginInformation) throws RemoteException {

        }

        @Override
        public void onContextPluginUninstalled(ContextPluginInformation contextPluginInformation) throws RemoteException {

        }

        @Override
        public void onContextPluginInstallFailed(ContextPluginInformation contextPluginInformation, String s) throws RemoteException {

        }

        @Override
        public void onContextRequestFailed(String s, String s1, int i) throws RemoteException {

        }

        @Override
        public void onContextPluginDiscoveryStarted() throws RemoteException {

        }

        @Override
        public void onContextPluginDiscoveryFinished(List<ContextPluginInformation> list) throws RemoteException {

        }

        @Override
        public void onDynamixFrameworkActive() throws RemoteException {

        }

        @Override
        public void onDynamixFrameworkInactive() throws RemoteException {

        }

        @Override
        public void onContextPluginError(ContextPluginInformation contextPluginInformation, String s) throws RemoteException {

        }

        @Override
        public void onContextPluginEnabled(ContextPluginInformation ontextPluginInformation) throws RemoteException {

        }

        @Override
        public void onContextPluginDisabled(ContextPluginInformation contextPluginInformation) throws RemoteException {

        }

        @Override
        public void onContextSupportResult(ContextSupportResult contextSupportResult) throws RemoteException {

        }
    };
}

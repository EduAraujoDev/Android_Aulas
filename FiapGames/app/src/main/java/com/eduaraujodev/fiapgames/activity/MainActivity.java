package com.eduaraujodev.fiapgames.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eduaraujodev.fiapgames.R;
import com.eduaraujodev.fiapgames.adapter.ListaPedidoAdapter;
import com.eduaraujodev.fiapgames.dao.PedidoDAO;
import com.eduaraujodev.fiapgames.model.Pedido;

import java.util.List;

import static com.eduaraujodev.fiapgames.activity.LoginActivity.KEY_APP_PREFERENCES;
import static com.eduaraujodev.fiapgames.activity.LoginActivity.KEY_LOGIN;
import static com.eduaraujodev.fiapgames.activity.LoginActivity.USER;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView tvSemPedido;
    private List<Pedido> pedidos;

    private RecyclerView rvLista;
    private ListaPedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvLista = (RecyclerView) findViewById(R.id.rvLista);
        tvSemPedido = (TextView) findViewById(R.id.tvSemPedido);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView tvLogin = (TextView) headerView.findViewById(R.id.tvLogin);
        tvLogin.setText(USER);

        carregaPedido();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_cadastrar:
                startActivityForResult(new Intent(MainActivity.this, CadastrarActivity.class),
                        CadastrarActivity.CODE_NOVO_PEDIDO);
                break;
            case R.id.nav_sair:
                sair();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == CadastrarActivity.CODE_NOVO_PEDIDO) {
            carregaPedido();
        }
    }

    private void carregaPedido() {
        pedidos = new PedidoDAO(this).getAll();

        if (pedidos.size() > 0) {
            tvSemPedido.setText("");

            adapter = new ListaPedidoAdapter(this, pedidos, pedidoOnClickListener());
            rvLista.setLayoutManager(new LinearLayoutManager(this));
            rvLista.setAdapter(adapter);
        } else {
            tvSemPedido.setText(getString(R.string.texto_sem_pedido));
        }
    }

    private void sair() {
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN, "");
        editor.apply();

        finish();
    }

    private ListaPedidoAdapter.PedidoOnClickListener pedidoOnClickListener() {
        return new ListaPedidoAdapter.PedidoOnClickListener() {

            @Override
            public void onClickPedido(View view, int posicao) {
                //Toast.makeText(getApplicationContext(), "onclick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickPedido(View view, final int posicao) {
                final Pedido pedido = pedidos.get(posicao);

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_excluir:
                                excluiPedido(pedido.getId(), posicao);

                                break;
                            case R.id.menu_alterar:
                                alteraPedido(pedido, posicao);

                                break;
                        }

                        carregaPedido();

                        return false;
                    }
                });

                popupMenu.show();
            }

            private void excluiPedido(long idPedido, int posicao) {
                if (new PedidoDAO(getApplicationContext()).delete(idPedido) > 0) {
                    Toast.makeText(getApplicationContext(), R.string.excluido_ok, Toast.LENGTH_LONG).show();

                    pedidos.remove(posicao);
                    adapter.notifyItemRemoved(posicao);
                    adapter.notifyItemRangeChanged(posicao, pedidos.size());
                } else {
                    Toast.makeText(getApplicationContext(), R.string.excluido_nok, Toast.LENGTH_LONG).show();
                }
            }

            private void alteraPedido(Pedido pedido, int posicao) {
                Intent intent = new Intent(getApplicationContext(), AlterarActivity.class);
                intent.putExtra("pedido", (int) pedido.getId());

                startActivityForResult(intent, AlterarActivity.CODE_ALTERA_PEDIDO);

                adapter.notifyItemRangeChanged(posicao, pedidos.size());
            }
        };
    }
}
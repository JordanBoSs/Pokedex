package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private GridView gridView;
    private Pokeadapter adapterListView, adapterGridView;

    List<Pokemon> pokemon;

    private MenuItem btnListview, btnGridView;

    private int contador = 0;
    private final int switchToLV = 0, switchToGV = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Forzar icono
        this.enforceIconBar();

        this.pokemon = getAllPokemon();

        this.listView = (ListView) findViewById(R.id.listView);
        this.gridView = (GridView) findViewById(R.id.gridView);

        this.listView.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);

        this.adapterListView = new Pokeadapter(this, R.layout.list_item, pokemon);
        this.adapterGridView = new Pokeadapter(this, R.layout.grid_item, pokemon);

        this.listView.setAdapter(adapterListView);
        this.gridView.setAdapter(adapterGridView);

        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);

    }

    private void enforceIconBar(){
        getSupportActionBar().setIcon(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
        this.clickPokemon(pokemon.get(posicion));
    }

    private void clickPokemon(Pokemon pokemon) {
        if(pokemon.getTipo().equals("Desconocido"))
            Toast.makeText(this, "Tipo de Pokemon Desconocido: " + pokemon.getNombre(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Tu " + pokemon.getNombre() + " es un pokemon tipo " + pokemon.getTipo(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        this.btnListview = menu.findItem(R.id.btnLV);
        this.btnGridView = menu.findItem(R.id.btnGV);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addPokemon:
                this.addPokemon(new Pokemon("Pokemon agregado " + (++contador), "Desconocido", R.mipmap.logo));
                return true;
            case R.id.btnGV:
                this.switchListGridView(this.switchToGV);
                return true;
            case R.id.btnLV:
                this.switchListGridView(this.switchToLV);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Inflamos el context menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        // Antes de inflar, le añadimos el header dependiendo del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.pokemon.get(info.position).getNombre());
        // Inflamos
        inflater.inflate(R.menu.context_menu_pokemon, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // Obtener info en el context menu del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_pokemon:
                this.deletePokemon(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void switchListGridView(int option) {
        // Método para cambiar entre Grid/List view
        if (option == switchToLV) {
            // Si queremos cambiar a list view, y el list view está en modo invisible...
            if (this.listView.getVisibility() == View.INVISIBLE) {
                // ... escondemos el grid view, y enseñamos su botón en el menú de opciones
                this.gridView.setVisibility(View.INVISIBLE);
                this.btnGridView.setVisible(true);
                // no olvidamos enseñar el list view, y esconder su botón en el menú de opciones
                this.listView.setVisibility(View.VISIBLE);
                this.btnListview.setVisible(false);
            }
        } else if (option == switchToGV) {
            // Si queremos cambiar a grid view, y el grid view está en modo invisible...
            if (this.gridView.getVisibility() == View.INVISIBLE) {
                // ... escondemos el list view, y enseñamos su botón en el menú de opciones
                this.listView.setVisibility(View.INVISIBLE);
                this.btnListview.setVisible(true);
                // no olvidamos enseñar el grid view, y esconder su botón en el menú de opciones
                this.gridView.setVisibility(View.VISIBLE);
                this.btnGridView.setVisible(false);
            }
        }
    }

    private List<Pokemon> getAllPokemon() {
        List<Pokemon> lista = new ArrayList<Pokemon>(){{
            add(new Pokemon("Bulbasaur", "Planta", R.mipmap.bulbasaur));
            add(new Pokemon("Charmander", "Fuego", R.mipmap.charmander));
            add(new Pokemon("Squirtle", "Agua", R.mipmap.squirtle));
            add(new Pokemon("Chikorita", "Planta", R.mipmap.chikorita));
            add(new Pokemon("Cyndaquil", "Fuego", R.mipmap.cyndaquil));
            add(new Pokemon("Totodile", "Agua", R.mipmap.totodile));
            add(new Pokemon("Treecko", "Planta", R.mipmap.treecko));
            add(new Pokemon("Torchic", "Fuego", R.mipmap.torchic));
            add(new Pokemon("Mudkip", "Agua", R.mipmap.mudkip));
            add(new Pokemon("Turtwig", "Planta", R.mipmap.turtwig));
            add(new Pokemon("Chimchar", "Fuego", R.mipmap.chimchar));
            add(new Pokemon("Piplup", "Agua", R.mipmap.piplup));
            add(new Pokemon("Snivy", "Planta", R.mipmap.snivy));
            add(new Pokemon("Tepig", "Fuego", R.mipmap.tepig));
            add(new Pokemon("Oshawott", "Agua", R.mipmap.oshawott));
            add(new Pokemon("Chespin", "Planta", R.mipmap.chespin));
            add(new Pokemon("Fennekin", "Fuego", R.mipmap.fennekin));
            add(new Pokemon("Frokie", "Agua", R.mipmap.froakie));
            add(new Pokemon("Rowlet", "Planta", R.mipmap.rowlet));
            add(new Pokemon("Litten", "Fuego", R.mipmap.litten));
            add(new Pokemon("Popplio", "Agua", R.mipmap.popplio));
            add(new Pokemon("Grookey", "Planta", R.mipmap.grookey));
            add(new Pokemon("Scorbunny", "Fuego", R.mipmap.scorbunny));
            add(new Pokemon("Sobble", "Agua", R.mipmap.sobble));
        }};
        return lista;
    }

    private void addPokemon(Pokemon pokemon) {
        this.pokemon.add(pokemon);
        // Avisamos del cambio en ambos adapters
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();

    }

    private void deletePokemon(int position) {
        this.pokemon.remove(position);
        // Avisamos del cambio en ambos adapters
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

}
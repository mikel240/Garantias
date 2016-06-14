package com.example.atmosfera.garantias.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.atmosfera.garantias.models.Factura;
import com.example.atmosfera.garantias.models.Garantia;
import com.example.atmosfera.garantias.models.Marca;

import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "DB_V";

    // Datos basicos de la DB
    private static final String DATABASE_NAME = "garantiator_db";
    private static final int DATABASE_VERSION = 1;

    // Nnombres de las tablas
    private static final String TABLE_FACTURA = "factura";
    private static final String TABLE_GARANTIA = "garantia";
    private static final String TABLE_MARCA = "marca";

    // SQLs para la creacion de las tablas
    private static final String SQL_ACTIVATE_FK = "PRAGMA FOREIGN_KEYS=ON";

    private static final String SQL_CREATE_MARCA = "CREATE TABLE " + TABLE_MARCA + " (" +
            "idM INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombreM TEXT UNIQUE NOT NULL, " +
            "correoM TEXT NOT NULL);";

    private static final String SQL_CREATE_FACTURA = "CREATE TABLE " + TABLE_FACTURA + " (" +
            "idF INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "rutaArchivoF TEXT NOT NULL, " +
            "formatoArchivoF TEXT NOT NULL);";

    private static final String SQL_CREATE_GARANTIA = "CREATE TABLE " + TABLE_GARANTIA + " (" +
            "idG INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipoProductoG TEXT NOT NULL, " +
            "marcaG INTEGER, " +
            "modeloG TEXT NOT NULL, " +
            "numeroSerieG TEXT NOT NULL, " +
            "detallesG TEXT NOT NULL, " +
            "fechaCompraG TEXT NOT NULL, " +
            "lugarCompraG TEXT NOT NULL, " +
            "duracionG INTEGER NOT NULL, " +
            "facturaG INTEGER, " +
            "FOREIGN KEY (marcaG) REFERENCES marca(idM) ON UPDATE CASCADE ON DELETE SET NULL, " +
            "FOREIGN KEY (facturaG) REFERENCES factura(idF) ON UPDATE CASCADE ON DELETE SET NULL);";

    // Patron Singleton para centralizar el acceso a la DB
    private static DataBaseHelper INSTANCE = null;

    // Creador sincronizado
    public synchronized static DataBaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseHelper(context.getApplicationContext());
        }
        return INSTANCE;
    }

    // Crear un objeto para gestionar la DB
    private DataBaseHelper(Context context) {
        // contexto a utilizar para abrir o crear la DB
        // nombre del archivo DB
        // fabrica para la creacion de objetos Cursor, null por defecto7
        // version de la DB, se utilizará para actualizar/bajar la DB
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la DB la primera vez que se accede
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQLs para crear las tablas
        db.execSQL(SQL_ACTIVATE_FK);
        db.execSQL(SQL_CREATE_MARCA);
        db.execSQL(SQL_CREATE_FACTURA);
        db.execSQL(SQL_CREATE_GARANTIA);
    }

    // Sse invoca cuando la DB necesita ser actualizada/rebajada, mejor DEJAR EN BLANCO hasta futuras versiones
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Actualizacion de la DB de la version oldVersion a newVersion
        // db.execSQL("DROP TABLE IF EXISTS marca");
        // db.execSQL("DROP TABLE IF EXISTS factura");
        // db.execSQL("DROP TABLE IF EXISTS garantia");
        // onCreate(db);
    }

    // Insertar una nueva marca en la DB y devolver su ID
    public long addMarca(String nombre, String correo) {

        long res = -1;

        // Obtener acceso a la DB en modo de escritura (vamos a hacer modificaciones en la DB)
        SQLiteDatabase db = getWritableDatabase();

        // Insertar una nueva marca en la tabla (autoincremental id)
        ContentValues cv = new ContentValues();
        cv.put("nombreM", nombre);
        cv.put("correoM", correo);

        // Iniciar transaccion, para asegurar la consistencia de los datos
        db.beginTransaction();
        try {
            // Realizar acciones
            res = db.insert(TABLE_MARCA, null, cv);

            // Confirmar transaccion, hacer las modificaiones efectivas
            db.setTransactionSuccessful();
        } catch (SQLiteException sqlex) {
            Log.e(DEBUG_TAG, sqlex.getMessage());
        } finally {
            // Cerrar transaccion y objeto DB
            db.endTransaction();
            db.close();
        }

        return res;
    }

    // Insertar una nueva factura/fotografia en la DB y devolver su ID
    public long addFactura(String ruta, String formato) {

        long res = -1;

        // Obtener acceso a la DB en modo de escritura (vamos a hacer modificaciones en la DB)
        SQLiteDatabase db = getWritableDatabase();

        // Insertar una nueva factura/fotografia en la tabla (autoincremental id)
        ContentValues cv = new ContentValues();
        cv.put("rutaArchivoF", ruta);
        cv.put("formatoArchivoF", formato);

        // Iniciar transaccion, para asegurar la consistencia de los datos
        db.beginTransaction();
        try {
            // Realizar acciones
            res = db.insert(TABLE_FACTURA, null, cv);

            // Confirmar transaccion, hacer las modificaiones efectivas
            db.setTransactionSuccessful();
        } catch (SQLiteException sqlex) {
            Log.e(DEBUG_TAG, sqlex.getMessage());
        } finally {
            // Cerrar transaccion y objeto DB
            db.endTransaction();
            db.close();
        }

        return res;
    }

    // Insertar una nueva garantia en la DB y devolver su ID
    public long addGarantia(String tipoProductoG, long marcaG, String modeloG,
                            String fechaCompraG, String lugarCompraG, int duracionG, long facturaG) {

        long res = -1;

        // Obtener acceso a la DB en modo de escritura (vamos a hacer modificaciones en la DB)
        SQLiteDatabase db = getWritableDatabase();

        // Insertar una nueva garantia en la tabla (autoincremental id)
        ContentValues cv = new ContentValues();
        cv.put("tipoProductoG", tipoProductoG);
        cv.put("marcaG", marcaG);
        cv.put("modeloG", modeloG);
        cv.put("numeroSerieG", "");
        cv.put("detallesG", "");
        cv.put("fechaCompraG", fechaCompraG);
        cv.put("lugarCompraG", lugarCompraG);
        cv.put("duracionG", duracionG);
        cv.put("facturaG", facturaG);

        // Iniciar transaccion, para asegurar la consistencia de los datos
        db.beginTransaction();
        try {
            // Realizar acciones
            res = db.insert(TABLE_GARANTIA, null, cv);

            // Confirmar transaccion, hacer las modificaiones efectivas
            db.setTransactionSuccessful();
        } catch (SQLiteException sqlex) {
            Log.e(DEBUG_TAG, sqlex.getMessage());
        } finally {
            // Cerrar transaccion y objeto DB
            db.endTransaction();
            db.close();
        }

        return res;
    }

    // Eliminar todas las marcas de la DB y devolver el numero de entradas eliminadas
    public int clearAllMarcas() {

        int res = -1;

        // Obtener acceso a la DB en modo de escritura (vamos a hacer modificaciones en la DB)
        SQLiteDatabase db = getWritableDatabase();

        // Iniciar transaccion, para asegurar la consistencia de los datos
        db.beginTransaction();
        try {
            // Realizar modificaciones
            // Eliminar todas las marca de la tabla
            res = db.delete(TABLE_MARCA, null, null);

            // Confirmar transaccion, hacer las modificaiones efectivas
            db.setTransactionSuccessful();
        } catch (SQLiteException sqlex) {
            Log.e(DEBUG_TAG, sqlex.getMessage());
        } finally {
            // Cerrar transaccion y objeto DB
            db.endTransaction();
            db.close();
        }

        // Cerrar el objeto DB
        db.close();

        return res;
    }

    // Obtener todas las garantias
    public ArrayList<Garantia> getGarantias() {

        ArrayList<Garantia> res = new ArrayList<>();
        Marca itemM;
        Factura itemF;
        Garantia itemG;

        // Obtener acceso a la DB en modo de lectura (NO vamos a hacer modificaciones en la DB)
        SQLiteDatabase db = getReadableDatabase();

        // Consultar tabla para obtener los datos de todas las entradas existentes
        String query = "SELECT * " +
                "FROM garantia G, marca M, factura F " +
                "WHERE G.marcaG=M.idM AND G.facturaG=F.idF " +
                "ORDER BY G.idG DESC;";

        Cursor c = db.rawQuery(query, null);

        // Recorrer los resultados
        while (c.moveToNext()) {

            // Crear un objeto para la entrada de la DB
            itemF = new Factura();
            itemM = new Marca();
            itemG = new Garantia();

            itemG.setIdG(c.getInt(c.getColumnIndex("idG")));
            itemG.setTipoProductoG(c.getString(c.getColumnIndex("tipoProductoG")));
            itemG.setMarcaG(c.getInt(c.getColumnIndex("marcaG")));
            itemG.setModeloG(c.getString(c.getColumnIndex("modeloG")));
            itemG.setNumeroSerieG(c.getString(c.getColumnIndex("numeroSerieG")));
            itemG.setDetallesG(c.getString(c.getColumnIndex("detallesG")));
            itemG.setFechaCompraG(c.getString(c.getColumnIndex("fechaCompraG")));
            itemG.setLugarCompraG(c.getString(c.getColumnIndex("lugarCompraG")));
            itemG.setDuracionG(c.getInt(c.getColumnIndex("duracionG")));
            itemG.setFacturaG(c.getInt(c.getColumnIndex("facturaG")));

            itemM.setIdM(c.getInt(c.getColumnIndex("idM")));
            itemM.setNombreM(c.getString(c.getColumnIndex("nombreM")));
            itemM.setCorreoM(c.getString(c.getColumnIndex("correoM")));

            itemF.setIdF(c.getInt(c.getColumnIndex("idF")));
            itemF.setRutaArchivoF(c.getString(c.getColumnIndex("rutaArchivoF")));
            itemF.setFormatoArchivoF(c.getString(c.getColumnIndex("formatoArchivoF")));

            itemG.setObjMarcaG(itemM);
            itemG.setObjFacturaG(itemF);

            // Agregar el objeto garantia a la lista de resultados
            res.add(itemG);
        }

        // Cerrar el cursor y el objeto DB
        c.close();
        db.close();

        return res;
    }

    // Obtener una garantia concreta
    public Garantia getGarantia(int id) {

        Marca m;
        Factura f;
        Garantia g = null;

        // Obtener acceso a la DB
        SQLiteDatabase db = getReadableDatabase();

        // Query the table to get the text and author for all existing entries
        String query = "SELECT * " +
                "FROM garantia G, marca M, factura F " +
                "WHRE G.marcaG=M.idM AND G.facturaG=F.idF AND G.idG=" + id + ";";
        Cursor c = db.rawQuery(query, null);

        // Go through the resulting cursor
        while (c.moveToNext()) {

            // Create a object for the given entry in the database
            f = new Factura();
            m = new Marca();
            g = new Garantia();

            g.setIdG(c.getInt(c.getColumnIndex("idG")));
            g.setTipoProductoG(c.getString(c.getColumnIndex("tipoProductoG")));
            g.setMarcaG(c.getInt(c.getColumnIndex("marcaG")));
            g.setModeloG(c.getString(c.getColumnIndex("modeloG")));
            g.setNumeroSerieG(c.getString(c.getColumnIndex("numeroSerieG")));
            g.setDetallesG(c.getString(c.getColumnIndex("detallesG")));
            g.setFechaCompraG(c.getString(c.getColumnIndex("fechaCompraG")));
            g.setLugarCompraG(c.getString(c.getColumnIndex("lugarCompraG")));
            g.setDuracionG(c.getInt(c.getColumnIndex("duracionG")));
            g.setFacturaG(c.getInt(c.getColumnIndex("facturaG")));

            m.setIdM(c.getInt(c.getColumnIndex("idM")));
            m.setNombreM(c.getString(c.getColumnIndex("nombreM")));
            m.setCorreoM(c.getString(c.getColumnIndex("correoM")));

            f.setIdF(c.getInt(c.getColumnIndex("idF")));
            f.setRutaArchivoF(c.getString(c.getColumnIndex("rutaArchivoF")));
            f.setFormatoArchivoF(c.getString(c.getColumnIndex("formatoArchivoF")));

            g.setObjMarcaG(m);
            g.setObjFacturaG(f);

        }

        // Close cursor and database helper
        c.close();
        db.close();

        return g;
    }

    // Obtener todas las marcas
    public ArrayList<Marca> getMarcas() {

        ArrayList<Marca> res = new ArrayList<>();
        Marca itemM;

        // Obtener acceso a la DB en modo de lectura (NO vamos a hacer modificaciones en la DB)
        SQLiteDatabase db = getReadableDatabase();

        // Consultar tabla para obtener los datos de todas las entradas existentes
        String query = "SELECT * " +
                "FROM marca M;" +
                "ORDER BY M.nombreM ASC;";

        Cursor c = db.rawQuery(query, null);

        // Recorrer los resultados
        while (c.moveToNext()) {

            // Crear un objeto para la entrada de la DB
            itemM = new Marca();

            itemM.setIdM(c.getInt(c.getColumnIndex("idM")));
            itemM.setNombreM(c.getString(c.getColumnIndex("nombreM")));
            itemM.setCorreoM(c.getString(c.getColumnIndex("correoM")));

            // Agregar el objeto garantia a la lista de resultados
            res.add(itemM);
        }

        // Cerrar el cursor y el objeto DB
        c.close();
        db.close();

        return res;
    }

}

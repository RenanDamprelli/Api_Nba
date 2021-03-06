package com.example.nba_api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nba_api.models.Player;

import java.util.ArrayList;
import java.util.List;

public class playerDAO {

    private ConexaoDB conexaodb;
    private SQLiteDatabase banco;

    public playerDAO (Context context){
        conexaodb = new ConexaoDB(context);
        banco = conexaodb.getWritableDatabase();
    }

    public long inserir(Player player){
        ContentValues values = new ContentValues();
        values.put("id", player.getNmId());
        values.put("nome", player.getNmPlayer());
        values.put("posicao", player.getPosPlayer());
        values.put("altura", player.getHeiPlayer());
        values.put("peso", player.getWeiPlayer());
        return banco.insert("player", null, values);
    }

    public List<Player> obterTodos(){
        List<Player> players = new ArrayList<>();
        Cursor cursor = banco.query("player", new String[]{"id", "nome", "posicao", "altura", "peso"},
        null, null, null, null, null);
        while(cursor.moveToNext()){
            Player a = new Player();
            a.setNmId(cursor.getString(0));
            a.setNmPlayer(cursor.getString(1));
            a.setPosPlayer(cursor.getString(2));
            a.setHeiPlayer(cursor.getString(3));
            a.setWeiPlayer(cursor.getString(4));
            players.add(a);
        }
        return players;
    }

}

package icesi.movil.login;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import icesi.movil.login.model.Comentario;

/**
 * Created by Domiciano on 01/05/2018.
 */

/*
    PARA USAR ESTE ADAPTADOR DEBE TENER EL ARCHIVO renglon.xml EN LA CARPETA LAYOUT
    DEBE TAMBIÉN USAR 
*/
public class Adaptador extends BaseAdapter{
    ArrayList<Comentario> comentarios;
    FirebaseStorage storage;
    Context context;



    public Adaptador(Context context){
        this.context = context;
        comentarios = new ArrayList<>();
        storage=FirebaseStorage.getInstance();


    }

    @Override
    public int getCount() {
        return comentarios.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //se ejecuta tantas veces como elementos existan en la lista
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //aprovechamos el cache del ListView
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.renglon, null);
        }

        TextView tv_comentario = convertView.findViewById(R.id.tv_comentario);
        Button like_btn= convertView.findViewById(R.id.like_btn);
        like_btn.setText(""+comentarios.get(position).getId());



        tv_comentario.setText(comentarios.get(position).getTexto());
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                db.getReference().child("comentarios").child(comentarios.get(position).getId()).child("likes").push().setValue("L");
            }
        });

        final ImageView renglon_img= convertView.findViewById(R.id.renglon_img);
        StorageReference ref= storage.getReference().child("comments").child(comentarios.get(position).getId());

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(renglon_img);

            }
        });
        return convertView;
    }


    public void agregarComentario(Comentario c) {

        comentarios.add(c);
        notifyDataSetChanged();
    }

    public void refreshComment(Comentario c) {

        int index = comentarios.indexOf(c);
        Comentario viejo= comentarios.get(index);
        viejo.setLikes(c.getLikes());
        notifyDataSetChanged();
    }
}

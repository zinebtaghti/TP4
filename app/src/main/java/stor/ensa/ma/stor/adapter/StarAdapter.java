package stor.ensa.ma.stor.adapter;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import stor.ensa.ma.stor.service.ShowService;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tp4.R;

import java.util.ArrayList;
import java.util.List;

import stor.ensa.ma.stor.beans.Show;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {
    private static final String TAG = "StarAdapter";
    private List<Show> shows;
    private List<Show> showsFilter;
    private Context context;
    private NewFilter mfilter;

    // Constructor
    public StarAdapter(Context context, List<Show> shows) {
        this.shows = shows;
        this.context = context;
        this.showsFilter = new ArrayList<>(shows);
        this.mfilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.show_item, viewGroup, false);
        final StarViewHolder holder = new StarViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null,
                        false);
                final ImageView img = popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView idss = popup.findViewById(R.id.idss);
                Bitmap bitmap =
                        ((BitmapDrawable)((ImageView)v.findViewById(R.id.img)).getDrawable()).getBitmap();
                img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar)v.findViewById(R.id.stars)).getRating());
                idss.setText(((TextView)v.findViewById(R.id.ids)).getText().toString());
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Notez : ")
                        .setMessage("Donner une note entre 1 et 5 :")
                        .setView(popup)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(idss.getText().toString());
                                Show show = ShowService.getInstance().findById(ids);
                                show.setStar(s);
                                ShowService.getInstance().update(show);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();
                dialog.show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull StarViewHolder starViewHolder, int i) {
        Log.d(TAG, "onBindView call ! " + i);
        Glide.with(context)
                .asBitmap()
                .load(showsFilter.get(i).getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(starViewHolder.img);
        starViewHolder.name.setText(showsFilter.get(i).getName().toUpperCase());
        starViewHolder.stars.setRating(showsFilter.get(i).getStar());
        starViewHolder.idss.setText(String.valueOf(showsFilter.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        return showsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }

    // ViewHolder class
    public class StarViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    // Custom filter class
    private class NewFilter extends Filter {
        private final StarAdapter adapter;

        public NewFilter(StarAdapter adapter) {
            super();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Show> filteredList = new ArrayList<>();
            final FilterResults results = new FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(shows);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Show show : shows) {
                    if (show.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(show);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            showsFilter.clear();
            showsFilter.addAll((List<Show>) filterResults.values);
            adapter.notifyDataSetChanged();
        }
    }
}

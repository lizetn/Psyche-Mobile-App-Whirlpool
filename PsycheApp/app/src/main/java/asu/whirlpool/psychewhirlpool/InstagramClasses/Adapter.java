package asu.whirlpool.psychewhirlpool.InstagramClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jperez60 on 1/4/2018.
 */
//recycler viewer adapter
public class Adapter extends RecyclerView.Adapter<Adapter.myholder>
{
    Context ctx;
    public static ArrayList<Model> path;
    public static int pos = 0;
    public static Bitmap b;
    public Adapter(Context ctx, ArrayList<Model> path) {
        this.ctx = ctx;
        this.path = path;
    }

    public Adapter.myholder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inf = LayoutInflater.from(ctx);
        View v = inf.inflate(R.layout.list,parent,false);
        myholder m = new myholder(v);
        return m;
    }
    public void onBindViewHolder(final Adapter.myholder holder, final int position)
    {

       Glide.with(ctx).load(path.get(position).getImage_url()).into(holder.imgs);
       Glide.with(ctx).load(MainInstagramActivity.profile_img).into(holder.iv_profile);
        holder.name.setText(MainInstagramActivity.profile_name);

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String likes = formatter.format(Integer.parseInt(path.get(position).getLikes()));
        holder.likes.setText(likes);
        String comments = formatter.format(Integer.parseInt(path.get(position).getComments()));
        holder.comments.setText(comments);



        final SpannableStringBuilder sb = new SpannableStringBuilder("Readmore..");
        final ForegroundColorSpan fcs = new ForegroundColorSpan(ctx.getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        holder.content.setText(path.get(position).getContent());
        holder.content.setVisibility(View.VISIBLE);

        holder.shor_content.setVisibility(View.GONE);


        holder.imgs.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v)
            {
                pos = position;
                Intent i = new Intent(ctx, Display.class);
                i.putExtra("Pos", pos);
                ctx.startActivity(i);
            }
        });

        holder.hearts.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.instagram.com/nasapsyche");
                Intent in = new Intent(Intent.ACTION_VIEW, uri);
                ctx.startActivity(in);
            }
            });
        holder.commentImg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.instagram.com/nasapsyche");
                Intent in = new Intent(Intent.ACTION_VIEW, uri);
                ctx.startActivity(in);
            }
        });
    }
    public int getItemCount() {
        return path.size();
    }
    class myholder extends RecyclerView.ViewHolder
    {
        CardView cv;
        ImageView imgs;
        ImageView hearts;
        ImageView commentImg;
        CircleImageView iv_profile;
        TextView name,
                likes,
                comments
                ,content
                ,shor_content;
        public myholder(View itemView)
        {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            imgs = (ImageView) itemView.findViewById(R.id.main_imgs);
            hearts = (ImageView) itemView.findViewById(R.id.hearts);
            commentImg = (ImageView) itemView.findViewById(R.id.commentsPic);
            iv_profile = (CircleImageView) itemView.findViewById(R.id.iv_profile);
            name = (TextView) itemView.findViewById(R.id.name);
            likes = (TextView) itemView.findViewById(R.id.likes);
            comments = (TextView) itemView.findViewById(R.id.comments);
            content = (TextView) itemView.findViewById(R.id.content);
            shor_content = (TextView) itemView.findViewById(R.id.shor_content);
        }
    }
}

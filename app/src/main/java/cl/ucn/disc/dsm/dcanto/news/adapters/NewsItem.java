/*
 * Copyright 2020 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.news.adapters;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.dcanto.news.R;
import cl.ucn.disc.dsm.dcanto.news.model.News;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mikepenz.fastadapter.items.ModelAbstractItem;
import org.threeten.bp.format.DateTimeFormatter;
import java.util.List;

/**
 * The NewsItem to show in the list.
 *
 * @author David Canto-Alcayaga
 */
public final class NewsItem extends ModelAbstractItem<News, NewsItem, NewsItem.ViewHolder> {

  /**
   * The ZonedDateTime formatter.
   */
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("HH:mm d.LLL.yyyy");

  /**
   * The Constructor
   *
   * @param news to show.
   */
  public NewsItem(@NonNull News news) { super(news); }

  /**
   * Get the view holder
   * @param view used to build the ViewHolder.
   * @return the ViewHolder to the views.
   */
  @NonNull
  @Override
  public ViewHolder getViewHolder(@NonNull View view) { return new ViewHolder(view); }

  @Override
  public int getType() { return R.id.am_rv_news; }

  /**
   * @return the layout to use
   */
  @Override
  public int getLayoutRes() {return R.layout.item_news; }

  public void bindView(@NonNull ViewHolder holder, @NonNull List<Object> payloads) {
    super.bindView(holder, payloads);

    // Setting the holder
    holder.title.setText(getModel().getTitle());
    holder.author.setText(getModel().getAuthor());
    holder.source.setText(getModel().getSource());
    holder.description.setText(getModel().getDescription());
    holder.publishedAt.setText(FORMATTER.format(getModel().getPublishedAt()));
    holder.urlImage.setImageURI(getModel().getUrlImage());
  }

  /**
   *  The ViewHolder pattern.
   */
  protected static class ViewHolder extends RecyclerView.ViewHolder{

    protected TextView title;
    protected TextView author;
    protected TextView source;
    protected SimpleDraweeView urlImage;
    protected TextView description;
    protected TextView publishedAt;

    public ViewHolder(@NonNull View view) {
      super(view);
      this.title = view.findViewById(R.id.in_tv_title);
      this.author = view.findViewById(R.id.in_tv_autor);
      this.source = view.findViewById(R.id.in_tv_source);
      this.urlImage = view.findViewById(R.id.my_image_view);
      this.description = view.findViewById(R.id.in_tv_description);
      this.publishedAt = view.findViewById(R.id.in_tv_published_at);
      Uri uri = Uri.parse(urlImage.toString());
      SimpleDraweeView draweeView = view.findViewById(R.id.my_image_view);
      draweeView.setImageURI(uri);
    }

  }
}



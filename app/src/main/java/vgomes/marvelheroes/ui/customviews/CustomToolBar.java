package vgomes.marvelheroes.ui.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vgomes.marvelheroes.R;
import vgomes.marvelheroes.interfaces.ISearchListener;

/**
 * Created by victorgomes on 01/07/17.
 */

public class CustomToolBar extends Toolbar {

    @BindView(R.id.search_input_et)
    EditText searchInputEt;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.clear_iv)
    ImageView clearIv;

    private ISearchListener listener;
    private int length;
    private String lastFilteredText = "";

    public CustomToolBar(Context context) {
        this(context, null);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.tool_bar, this);
        ButterKnife.bind(this);
        searchIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleTv.getVisibility() == VISIBLE) {
                    titleTv.setVisibility(GONE);
                    searchInputEt.setVisibility(VISIBLE);
                } else {
                    searchInputEt.setText("");
                    clearIv.setVisibility(GONE);
                    titleTv.setVisibility(VISIBLE);
                    searchInputEt.setVisibility(GONE);
                }
            }
        });
        clearIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                searchInputEt.setText("");
            }
        });
        searchInputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                length = editable.length();
                if (length > 0) {
                    clearIv.setVisibility(VISIBLE);
                } else {
                    clearIv.setVisibility(GONE);
                }
                if (length >= 3) {
                    if (listener != null) {
                        lastFilteredText = editable.toString();
                        listener.onSearchChanged(lastFilteredText);
                    }
                } else {
                    if (listener != null) {
                        if (!TextUtils.isEmpty(lastFilteredText)) {
                            lastFilteredText = "";
                            listener.onSearchChanged(lastFilteredText);
                        }
                    }
                }
            }
        });
    }

    public void setSearchChangedListener(ISearchListener listener) {
        this.listener = listener;
    }
}

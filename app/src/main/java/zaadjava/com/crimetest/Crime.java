package zaadjava.com.crimetest;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zulup on 4/26/2017.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    public Crime() {
        this(UUID.randomUUID());
    }
    public Crime(UUID uid) {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Date getDate() {
        return this.mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public boolean getSolved() {
        return mSolved;
    }

    public void setSolved(Boolean solved) {
        this.mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}

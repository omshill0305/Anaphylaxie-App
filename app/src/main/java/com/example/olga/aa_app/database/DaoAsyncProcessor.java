package com.example.olga.aa_app.database;

import android.os.AsyncTask;

/**
 *
 * @param <T> type of result expected
 */
public abstract class DaoAsyncProcessor<T> {

    public interface DaoProcessCallback<T>{
        void  onResult(T result);
    }

    private DaoProcessCallback daoProcessCallback;

    public DaoAsyncProcessor(){
    }

    public DaoAsyncProcessor(DaoProcessCallback daoProcessCallback) {
        this.daoProcessCallback = daoProcessCallback;
    }

    protected abstract T doAsync();

    public void start(){
        new DaoProcessAsyncTask().execute();
    }

    private static class DaoProcessAsyncTask extends AsyncTask<Void, Void, T> {

        @Override
        protected T doInBackground(Void... params) {
            return doAsync();
        }

        @Override
        protected void onPostExecute(T t) {
            if(daoProcessCallback != null)
                daoProcessCallback.onResult(t);
        }
    }
}

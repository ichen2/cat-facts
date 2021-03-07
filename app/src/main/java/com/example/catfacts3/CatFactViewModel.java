package com.example.catfacts3;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatFactViewModel extends ViewModel {
    private CatFactRepo catFactRepo = new CatFactRepo();
    private MutableLiveData<List<CatFact>> catFactLiveData = new MutableLiveData<>();
    public LiveData<List<CatFact>> catFact = catFactLiveData;

    public CatFactViewModel() {
        catFactLiveData.setValue(new ArrayList<>());
    }
    public void getCatFact(int numFacts) {
        if(numFacts > 0) {
            catFactRepo.requestCatFact(new Callback<CatFact>() {
                @Override
                public void onResponse(Call<CatFact> call, Response<CatFact> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        catFactLiveData.getValue().add(response.body());
                        catFactLiveData.setValue(catFactLiveData.getValue());
                        getCatFact(numFacts - 1);
                    } else {
                        Log.e("webRequestFailure", "getCatFact was null or unsuccessful");
                    }
                }

                @Override
                public void onFailure(Call<CatFact> call, Throwable t) {
                    Log.e("webRequestFailure", "getCatFact() failed");
                    t.printStackTrace();
                }
            });
        }
    }
}

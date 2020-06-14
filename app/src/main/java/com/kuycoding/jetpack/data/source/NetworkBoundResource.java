package com.kuycoding.jetpack.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.kuycoding.jetpack.data.source.remote.ApiResponse;
import com.kuycoding.jetpack.utils.AppExecutors;
import com.kuycoding.jetpack.vo.Resource;

public abstract class NetworkBoundResource<ResultType, RequestType>{
    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors mExecutors;

    private void onFetchFailed() {

    }

    protected abstract LiveData<ResultType> loadFromDB();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallRequest(RequestType data);

    public NetworkBoundResource(AppExecutors mExecutors) {
        this.mExecutors = mExecutors;
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDB();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.status) {
                case SUCCESS:
                    mExecutors.diskIO().execute(() -> {
                        saveCallRequest(response.body);
                        mExecutors.mainThread().execute(() ->
                                result.addSource(loadFromDB(),
                                newData -> result.setValue(Resource.success(newData))));
                    });
                    break;
                case EMPTY:
                    mExecutors.mainThread().execute(() ->
                            result.addSource(loadFromDB(),
                            newData -> result.setValue(Resource.success(newData))));
                    break;
                case ERROR:
                    onFetchFailed();
                    result.addSource(dbSource, newData ->
                            result.setValue(Resource.error(response.message, newData)));
                    break;
            }
        });
    }
    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}

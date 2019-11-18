package signin;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyStorageFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private View lview;


    public MyStorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lview = inflater.inflate(R.layout.fragment_my_storage, container, false);
        lview.findViewById(R.id.btnStorage).setOnClickListener(new View.OnClickListener() {

                                                                   @Override
                                                                   public void onClick(View view) {
                                                                       selectImage();
                                                                   }
                                                               }

        );
        return lview;
    }

    public void onStart() {
        super.onStart();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

    }

    public void selectImage() {
        Log.d("MyStorageFragment", "Selecionando a Imagem");
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MyStorageFragment", "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    final Uri uri = data.getData();
                    Log.d("MyStorageFragment", "Uri: " + uri.toString());

                    mFirebaseAuth = FirebaseAuth.getInstance();
                    mFirebaseUser = mFirebaseAuth.getCurrentUser();

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    String key = databaseReference.getKey();
                    StorageReference storageReference =
                            FirebaseStorage.getInstance()
                                    .getReference(mFirebaseUser.getUid())
                                    .child("respostas")
                                    .child(uri.getLastPathSegment());

                    putImageInStorage(storageReference, uri, key);
                }
            }
        }
    }

    private void putImageInStorage(StorageReference storageReference, Uri uri, final String key) {
        storageReference.putFile(uri).addOnCompleteListener(getActivity(),
                new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            task.getResult().getMetadata().getReference().getDownloadUrl()
                                    .addOnCompleteListener(getActivity(),
                                            new OnCompleteListener<Uri>() {
                                                @Override
                                                public void onComplete(Task<Uri> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.v("MyStorageFragment URL", task.getResult().toString());
                                                    }
                                                }
                                            });
                        } else {
                            Log.w("MyStorageFragment", "Image upload task was not successful.",
                                    task.getException());
                        }
                    }
                });
    }

}

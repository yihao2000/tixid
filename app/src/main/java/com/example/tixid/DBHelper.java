package com.example.tixid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "TIXID.db";
    SQLiteDatabase db;
    List<Theatre> theatreList = new ArrayList<>();
    ArrayList<Movie> movieList = new ArrayList<>();
    ArrayList<Movie> comingSoonMovieList = new ArrayList<>();

    public DBHelper(@Nullable Context context ) {
        super(context, "TIXID.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT NOT NULL, phonenumber TEXT NOT NULL)");
        MyDB.execSQL("create Table theatres(name TEXT primary key, description TEXT NOT NULL, longitude DOUBLE NOT NULL, latitude DOUBLE NOT NULL)");
        MyDB.execSQL("create Table movies(name TEXT primary key, description TEXT NOT NULL, imageurl TEXT NOT NULL, genre TEXT NOT NULL, duration TEXT NOT NULL)");
        MyDB.execSQL("create Table comingsoonmovies(name TEXT primary key, description TEXT NOT NULL, imageurl TEXT NOT NULL, genre TEXT NOT NULL, duration TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists theatres");
        MyDB.execSQL("drop Table if exists movies");
        MyDB.execSQL("drop Table if exists comingSoonMovies");
    }


    public Boolean insertData(String username, String password, String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phonenumber", phone);

        long result = MyDB.insert("users", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public void insertTheatres(){
        theatreSeeder("Aeon Mall BSD City XXI", "AEON Mall BSD City LT. 3 Jl. Raya BSD Utama Boulevard Barat Kec. Pagedangan - Kab. Tangerang", 106.64389, -6.304167);
        theatreSeeder("Alam Sutera XXI", "Mall Alam Sutera Lantai 2 No. 30D, Jl. Jalur Sutera Bar. No.18, RT.002/RW.003, Panunggangan Tim., Kec. Pinang, Kota Tangerang, Banten 15143", 106.653848535, -6.222621202);
        theatreSeeder("Bale Kota XXI", "Mall Bale Kota, Jl. Jenderal Sudirman No.Km. 10, RT.002/RW.012, Buaran Indah, Kec. Tangerang, Kota Tangerang, Banten 15119",  106.63048, -6.17298);
        theatreSeeder("Bintaro XChange XXI", " Jl. Tegal Rotan Raya No.24, Pd. Jaya, Kec. Pd. Aren, Kota Tangerang Selatan, Banten 15220", 106.728057266, -6.284924902);
        theatreSeeder("Bintaro XXI", "Bintaro Mall, Jl. Bintaro Utama 3A, Pd. Karya, Kec. Pd. Aren, Kota Tangerang Selatan, Banten 15225", 106.7429637, -6.2667358);
        theatreSeeder("Cikupa XXI", "Ramayana Cikupa, Jl. Raya Serang No.100, Talagasari, Kec. Cikupa, Kabupaten Tangerang, Banten 15710", 106.5150, -6.2283);
        theatreSeeder("Living World XXI", "Living World Lantai 2 Jalan Alam Sutera Boulevard Kav.21 Alam Sutera, Pakulonan, Kec. Serpong, Kota Tangerang Selatan, Banten 15325", 106.6535293, -6.2444807);

    }

    public void theatreSeeder(String name, String description, double longitude, double latitude){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);

        myDB.insert("theatres", null, contentValues);

    }

    public void insertMovies(){
        movieSeeder("Black Light", "Travis Block, a shadowy government agent who specializes in removing operatives whose covers have been exposed, uncovers a deadly conspiracy within his own ranks that reaches the highest echelons of power.", "https://i.ibb.co/G9Y4tWV/blacklight.jpg", "Action", "1 Hour 30 minutes");
        movieSeeder("The Secrets of Dumbledore", "Professor Albus Dumbledore knows the powerful, dark wizard Gellert Grindelwald is moving to seize control of the wizarding world. Unable to stop him alone, he entrusts magizoologist Newt Scamander to lead an intrepid team of wizards and witches. They soon encounter an array of old and new beasts as they clash with Grindelwald's growing legion of followers.", "https://i.ibb.co/1TDcdDH/dumbledore.jpg", "Adventure", "2 Hours 45 minutes");
        movieSeeder("Kukira Kau Rumah", "Film ini menceritakan tentang pemuda bernama Pram (Jourdy Pranata) yang kesepian. Sang ayah meninggal ketika ia masih di sekolah menengah, sedangkan ibunya sibuk bekerja. Pram kemudian mengisi hari-harinya dengan bermain musik dan mengarang lagu sambil bekerja di kafe musik.", "https://i.ibb.co/bvGk3Ys/kukirakaurumah.jpg", "Romance", "2 Hours 15 minutes");
        movieSeeder("Sonic the Movie", "The world needed a hero -- it got a hedgehog. Powered with incredible speed, Sonic embraces his new home on Earth -- until he accidentally knocks out the power grid, sparking the attention of uncool evil genius Dr. Robotnik. Now, it's supervillain vs. supersonic in an all-out race across the globe to stop Robotnik from using Sonic's unique power to achieve world domination.", "https://i.ibb.co/RDkWZK0/sonic.jpg", "Cartoon", "1 Hours 45 minutes");
    }





    public void movieSeeder(String name, String description, String url, String genre, String duration){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("imageurl", url);
        contentValues.put("genre", genre);
        contentValues.put("duration", duration);
        myDB.insert("movies", null, contentValues);
    }


    public void insertComingSoonMovies(){

        //<a href="https://ibb.co/X4D6RDv"><img src="https://i.ibb.co/26SR4Sf/Doctor-Strange.jpg" alt="Doctor-Strange" border="0"></a>
        //<a href="https://ibb.co/sy9LLn6"><img src="https://i.ibb.co/NZ2ww5m/forceofnature.jpg" alt="forceofnature" border="0"></a>
        //<a href="https://ibb.co/NtLFw1h"><img src="https://i.ibb.co/5G51qYD/theambush.jpg" alt="theambush" border="0"></a>
        //<a href="https://ibb.co/d2n9J25"><img src="https://i.ibb.co/KqCZzqr/topgunmaverick.jpg" alt="topgunmaverick" border="0"></a>

        comingSoonMovieSeeder("Doctor Strange", "Dr. Stephen Strange casts a forbidden spell that opens the doorway to the multiverse, including alternate versions of himself, whose threat to humanity is too great for the combined forces of Strange, Wong, and Wanda Maximoff.", "https://i.ibb.co/26SR4Sf/Doctor-Strange.jpg", "Action", "2 Hours 6 minutes");
        comingSoonMovieSeeder("Force of Nature", "A gang of thieves plan a heist during a hurricane and encounter trouble when a cop tries to force everyone in the building to evacuate. A gang of thieves plan a heist during a hurricane and encounter trouble when a cop tries to force everyone in the building to evacuate.", "https://i.ibb.co/NZ2ww5m/forceofnature.jpg", "Crime", "1 Hour 31 Minutes");
        comingSoonMovieSeeder("The Ambush", "When three Emirati soldiers are ambushed in a hostile territory, their commander leads a daring mission to rescue them. When three Emirati soldiers are ambushed in a hostile territory, their commander leads a daring mission to rescue them.", "https://i.ibb.co/5G51qYD/theambush.jpg", "Drama", "1 Hour 42 Minutes");
        comingSoonMovieSeeder("Top Gun Mavering", "After more than 30 years of service as one of the Navy's top aviators, Pete \"Maverick\" Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him. Training a detachment of graduates for a special assignment, Maverick must confront the ghosts of his past and his deepest fears, culminating in a mission that demands the ultimate sacrifice from those who choose to fly it.", "https://i.ibb.co/KqCZzqr/topgunmaverick.jpg", "Action", "2 Hours 5 minutes");
    }


    public void comingSoonMovieSeeder(String name, String description, String url, String genre, String duration){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("imageurl", url);
        contentValues.put("genre", genre);
        contentValues.put("duration", duration);
        myDB.insert("comingsoonmovies", null, contentValues);
    }


    public User findUserCredential(String accountUsername) {

        User user;
        db = getWritableDatabase();

        String columns[] = {"username", "password", "phonenumber"};
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{accountUsername});

      if(cursor.moveToFirst()) {
          int index1 = cursor.getColumnIndex("username");
          String username = cursor.getString(index1);
          int index2 = cursor.getColumnIndex("password");
          String password = cursor.getString(index2);
          int index3 = cursor.getColumnIndex("phonenumber");
          String phoneNumber = cursor.getString(index3);
            return new User(username, password, phoneNumber);
      }
      return new User("Username", "Password", "Phone Number");

    }


    public ArrayList<Movie> getAllMovies(){
        db = getWritableDatabase();

        String columns[] = {"name", "description", "imageurl", "genre", "duration"};
        Cursor cursor = db.query("movies", columns,null, null,null, null, null, null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex("name");
            String name = cursor.getString(index1);
            int index2 = cursor.getColumnIndex("description");
            String description = cursor.getString(index2);
            int index3 = cursor.getColumnIndex("imageurl");
            String imageUrl = cursor.getString(index3);
            int index4 = cursor.getColumnIndex("genre");
            String genre = cursor.getString(index4);
            int index5 = cursor.getColumnIndex("duration");
            String duration = cursor.getString(index5);
            Movie movie = new Movie(name, description, imageUrl, genre, duration);
            movieList.add(movie);
        }
        return movieList;
    }

    public ArrayList<Movie> getComingSoonMovie(){
        db = getWritableDatabase();

        String columns[] = {"name", "description", "imageurl", "genre", "duration"};
        Cursor cursor = db.query("comingsoonmovies", columns,null, null,null, null, null, null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex("name");
            String name = cursor.getString(index1);
            int index2 = cursor.getColumnIndex("description");
            String description = cursor.getString(index2);
            int index3 = cursor.getColumnIndex("imageurl");
            String imageUrl = cursor.getString(index3);
            int index4 = cursor.getColumnIndex("genre");
            String genre = cursor.getString(index4);
            int index5 = cursor.getColumnIndex("duration");
            String duration = cursor.getString(index5);
            Movie movie = new Movie(name, description, imageUrl, genre, duration);
            comingSoonMovieList.add(movie);
        }
        return comingSoonMovieList;
    }




    public List<Theatre> getAllTheatres(){
        db = getWritableDatabase();

        String columns[] = {"name", "description", "longitude", "latitude"};
        Cursor cursor = db.query("theatres", columns,null, null,null, null, null, null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex("name");
            String rowid = cursor.getString(index1);
            int index2 = cursor.getColumnIndex("description");
            String description = cursor.getString(index2);
            int index3 = cursor.getColumnIndex("longitude");
            double longitude = cursor.getDouble(index3);
            int index4 = cursor.getColumnIndex("latitude");
            double latitude = cursor.getDouble(index4);
            Theatre theatre = new Theatre(rowid, description, longitude, latitude);
            theatreList.add(theatre);
        }
        return theatreList;

    }




    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});

        if(cursor.getCount()>0)return true;

        else{
            return false;
        }
    }


    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    public void updateProfileOldPassword(String currUsername, String username, String password, String phonenumber){
        SQLiteDatabase myDb = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phonenumber", phonenumber);

        myDb.update("users", contentValues, "username=?", new String[]{currUsername});
    }


}

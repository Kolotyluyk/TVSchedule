package com.sk.tvschedule.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import com.sk.tvschedule.model.Category;

/**
 * Created by Сергій on 04.02.2017.
 */

public class ContractClass {
    public static final String AUTHORITY = "com.sk.tvschedule.provider.ContractClass";
    public static final class Category implements BaseColumns {

        private Category() {}
        public static final String SCHEME = "content://";
        public static final String tableCategory ="Category";

        public static final String PATH_CATEGORY= "Category";
        public static final String PATH_CATEGORY_ID= "/Category/#";

        public static final int CATEGORY_ID_PATH_POSITION = 1;

        public static final String columnCategoryId ="_id";
        public static final String columnCategoryTitle ="title";
        public static final String columnCategoryPicture ="picture";

        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY +"/"+ PATH_CATEGORY);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY +"/"+ PATH_CATEGORY_ID);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+ AUTHORITY+"."+PATH_CATEGORY;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORITY+"."+PATH_CATEGORY;

        public static final String DEFAULT_SORT_ORDER = "_id ASC";

        public static final String[] DEFAULT_PROJECTION= new String[]{
                Category.columnCategoryId,
                Category.columnCategoryPicture,
                Category.columnCategoryTitle
        };
    }

    public static final class Channel implements BaseColumns {

        private Channel() {}
        public static final String SCHEME = "content://";
        public static final String tableChannel ="Channel";

        public static final String PATH_Channel= "Channel";
        public static final String PATH_Channel_ID= "/Channel/#";

        public static final int CHANNEL_ID_PATH_POSITION = 1;

        public static final String columnChannelId ="_id";
        public static final String columnChannelName ="name";
        public static final String columnChannelCategorId ="category_id";
        public static final String columnChannelURL ="url";
        public static final String columnChannelPicture ="picture";

        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY +"/"+ PATH_Channel);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_Channel_ID);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+ AUTHORITY+"."+PATH_Channel;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORITY+"."+PATH_Channel;

        public static final String DEFAULT_SORT_ORDER = "_id ASC";

        public static final String[] DEFAULT_PROJECTION= new String[]{
                ContractClass.Channel.columnChannelId,
                ContractClass.Channel.columnChannelName ,
                ContractClass.Channel.columnChannelCategorId,
                ContractClass.Channel.columnChannelURL ,
                ContractClass.Channel.columnChannelPicture
        };
    }


    public static final class Program implements BaseColumns {

        private Program() {}
        private static final String SCHEME = "content://";
        public static final String tableProgram ="Program";

        public static final String PATH_Program= "Program";
        public static final String PATH_Program_ID= "/Program/#";

        public static final int PROGRAM_ID_PATH_POSITION = 1;

        public static final String columnProgramId ="_id";
        public static final String columnProgramChannelId ="channel_id";
        public static final String columnProgramTitle ="title";
        public static final String columnProgramTime ="p_time";
        public static final String columnProgramDate ="p_date";
        public static final String columnProgramDescription ="description";

        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY +"/"+ PATH_Program);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_Program_ID);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+ AUTHORITY+"."+PATH_Program;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORITY+"."+PATH_Program;

        public static final String DEFAULT_SORT_ORDER = "_id ASC";

        public static final String[] DEFAULT_PROJECTION= new String[]{
                ContractClass.Program.columnProgramId,
                ContractClass.Program.columnProgramChannelId ,
                ContractClass.Program.columnProgramTitle,
                ContractClass.Program.columnProgramTime,
                ContractClass.Program.columnProgramDate,
                ContractClass.Program.columnProgramDescription
        };
    }
    public static final class Favorite implements BaseColumns {

        private Favorite() {}
        public static final String SCHEME = "content://";
        public static final String tableFavorite ="Favorite";

        public static final String PATH_Favorite= "Favorite";
        public static final String PATH_Favorite_ID= "/Favorite/#";

        public static final int FAVORITE_ID_PATH_POSITION = 1;

        public static final String columnFavoriteID ="_id";
        public static final String columnFavoriteChannelID ="channel_id";

        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY +"/"+ PATH_Favorite);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_Favorite_ID);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+ AUTHORITY+"."+PATH_Favorite;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORITY+"."+PATH_Favorite;

        public static final String DEFAULT_SORT_ORDER = "_id ASC";

        public static final String[] DEFAULT_PROJECTION= new String[]{
                ContractClass.Favorite.columnFavoriteID,
                ContractClass.Favorite.columnFavoriteChannelID ,
                    };
    }
}

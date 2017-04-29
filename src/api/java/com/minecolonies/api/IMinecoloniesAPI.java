package com.minecolonies.api;

import com.minecolonies.api.citizen.lumberjack.tree.ITreeManagerController;

/**
 * The central API Interface for Armory.
 */
public interface IMinecoloniesAPI
{

    /**
     * Method used to get the controller for {@link com.minecolonies.api.citizen.lumberjack.tree.ITreeManager}
     *
     * @return The controller for {@link com.minecolonies.api.citizen.lumberjack.tree.ITreeManager}
     */
    ITreeManagerController getTreeManager();

    class Holder
    {
        private static IMinecoloniesAPI api = null;

        public static IMinecoloniesAPI getApi()
        {
            return api;
        }

        public static void setApi(final IMinecoloniesAPI api)
        {
            if (api != null)
            {
                throw new IllegalStateException("API Already set!");
            }

            Holder.api = api;
        }
    }
}

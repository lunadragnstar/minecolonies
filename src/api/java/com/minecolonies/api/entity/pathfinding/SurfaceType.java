package com.minecolonies.api.entity.pathfinding;

/**
 * Check if we can walk on a surface, drop into, or neither.
 */
public enum SurfaceType
{
    /**
     * The entity can walk over it.
     */
    WALKABLE,

    /**
     * Should be avoided.
     */
    DROPABLE,

    /**
     * Cannot be passed.
     */
    NOT_PASSABLE
}

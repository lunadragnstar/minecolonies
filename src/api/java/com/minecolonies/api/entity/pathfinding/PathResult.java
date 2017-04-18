package com.minecolonies.api.entity.pathfinding;

/**
 * Creates a pathResult of a certain path.
 */
public class PathResult implements com.minecolonies.api.entity.pathfinding.IPathResult
{
    protected volatile PathFindingStatus status                 = PathFindingStatus.IN_PROGRESS_COMPUTING;
    protected volatile boolean           pathReachesDestination = false;
    protected volatile int               pathLength             = 0;

    /**
     * Public constructor of the path result.
     */
    public PathResult()
    {
        /**
         * Intentionally left empty.
         */
    }

    /**
     * Get Status of the Path.
     *
     * @return status.
     */
    @Override
    public PathFindingStatus getStatus()
    {
        return status;
    }

    /**
     * For PathNavigate and AbstractPathJob use only.
     *
     * @param s status to set.
     */
    @Override
    public void setStatus(final PathFindingStatus s)
    {
        status = s;
    }

    /**
     * @return true if the path is still computing or being followed.
     */
    @Override
    public boolean isInProgress()
    {
        return isComputing() || status == PathFindingStatus.IN_PROGRESS_FOLLOWING;
    }

    @Override
    public boolean isComputing()
    {
        return status == PathFindingStatus.IN_PROGRESS_COMPUTING;
    }

    /**
     * @return true if the no path can be found.
     */
    @Override
    public boolean failedToReachDestination()
    {
        return !isComputing() && !getPathReachesDestination();
    }

    /**
     * @return true if the path is computed, and it reaches a desired destination.
     */
    @Override
    public boolean getPathReachesDestination()
    {
        return pathReachesDestination;
    }

    /**
     * For PathNavigate and AbstractPathJob use only.
     *
     * @param value new value for pathReachesDestination.
     */
    @Override
    public void setPathReachesDestination(final boolean value)
    {
        pathReachesDestination = value;
    }

    /**
     * @return true if the path was cancelled before being computed or before the entity reached it's destination.
     */
    @Override
    public boolean isCancelled()
    {
        return status == PathFindingStatus.CANCELLED;
    }

    /**
     * @return length of the compute path, in nodes.
     */
    @Override
    public int getPathLength()
    {
        return pathLength;
    }

    /**
     * For PathNavigate use only.
     *
     * @param l new value for pathLength.
     */
    @Override
    public void setPathLength(final int l)
    {
        pathLength = l;
    }

    /**
     * @return true if the path moves from the current location, useful for checking if a path actually generated.
     */
    @Override
    public boolean didPathGenerate()
    {
        return pathLength > 0;
    }
}

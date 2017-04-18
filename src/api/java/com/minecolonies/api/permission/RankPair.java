package com.minecolonies.api.permission;

/**
 * ------------ Class not Documented ------------
 */
public class RankPair
{
    /**
     * The rank if promoted.
     */
    private final Rank promote;

    /**
     * The rank if demoted.
     */
    private final Rank demote;

    /**
     * Links promotion and demotion.
     *
     * @param p Promoting rank.
     * @param d Demoting rank.
     */
    public RankPair(final Rank p, final Rank d)
    {
        promote = p;
        demote = d;
    }

    public Rank getPromote()
    {
        return promote;
    }

    public Rank getDemote()
    {
        return demote;
    }
}

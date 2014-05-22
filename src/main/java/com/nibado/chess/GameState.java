package com.nibado.chess;

public class GameState {
    private boolean _kingWhiteMoved;
    private boolean _kingBlackMoved;
    private boolean _rookQsWhiteMoved;
    private boolean _rookQsBlackMoved;
    private boolean _rookKsWhiteMoved;
    private boolean _rookKsBlackMoved;

    public boolean isKingWhiteMoved() {
        return _kingWhiteMoved;
    }

    public void setKingWhiteMoved() {
        _kingWhiteMoved = true;
    }

    public boolean isKingBlackMoved() {
        return _kingBlackMoved;
    }

    public void setKingBlackMoved() {
        _kingBlackMoved = true;
    }

    public boolean isRookQsWhiteMoved() {
        return _rookQsWhiteMoved;
    }

    public void setRookQsWhiteMoved() {
        _rookQsWhiteMoved = true;
    }

    public boolean isRookQsBlackMoved() {
        return _rookQsBlackMoved;
    }

    public void setRookQsBlackMoved() {
        _rookQsBlackMoved = true;
    }

    public boolean isRookKsWhiteMoved() {
        return _rookKsWhiteMoved;
    }

    public void setRookKsWhiteMoved() {
        _rookKsWhiteMoved = true;
    }

    public boolean isRookKsBlackMoved() {
        return _rookKsBlackMoved;
    }

    public void setRookKsBlackMoved() {
        _rookKsBlackMoved = true;
    }

    @Override
    public boolean equals(final Object o) {
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        final GameState other = (GameState) o;

        return _kingWhiteMoved == other._kingWhiteMoved &&
            _kingBlackMoved == other._kingBlackMoved &&
            _rookQsWhiteMoved == other._rookQsWhiteMoved &&
            _rookQsBlackMoved == other._rookQsBlackMoved &&
            _rookKsWhiteMoved == other._rookKsWhiteMoved &&
            _rookKsBlackMoved == other._rookKsBlackMoved;
    }

}

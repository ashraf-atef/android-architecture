package com.example.android.architecture.blueprints.todoapp.mvibase;

/**
 * Used as a wrapper for data that is exposed via streams that replays the previous emitted items
 * like subjects that uses replay operator or behaviour subjects, to represent an event that
 * should be handled once.
 *
 * Example: We have a state that contains flag that indicates empty field and with each screen
 * rotation the view handles the same error like showing a snack bar. However this error is handled
 * before and we shouldn't handle it again. The solution is that we need to make a wrapper that
 * contains the data and a flag that indicates if the data or the event is handled before or not.
 */
public class Event<T> {

    private T content;
    private boolean handled = false;

    public static <T> Event<T> idle(T content) {
        Event<T> event = new Event<>(content);
        event.setHandled(true);
        return event;
    }

    public Event(T content) {
        if (content == null)
            throw new IllegalArgumentException("null values in Event are not allowed.");

        setContent(content);
    }

    /**
     * @return null if the data handle before and return the data if not.
     */
    public T getContent() {
        if (isHandled())
            return null;
        else {
            setHandled(true);
            return content;
        }
    }

    private void setContent(T content) {
        this.content = content;
    }

    private boolean isHandled() {
        return handled;
    }

    private void setHandled(boolean handled) {
        this.handled = handled;
    }
}

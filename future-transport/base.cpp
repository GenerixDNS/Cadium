/*
 * Created by Alexander J. on 02.01.2023.
 */

#include "base.h"

class FutureNotFoundException : public std::exception
{
private:
    std::string m_message;

public:

    explicit FutureNotFoundException(const char* message) : m_message(message)
    {
    }

    [[nodiscard]]
    const char* what() const noexcept override { return m_message.c_str(); }

};

static std::vector<std::pair<JPointer, std::future<std::any>*>> CACHED_FUTURES;

std::future<std::any>* GET_BY_UNIQUE(JPointer pointer)
{
    for (const auto &item: CACHED_FUTURES)
    {
        if (item.first == pointer)
        {
            return item.second;
        }
    }
    throw FutureNotFoundException("A Future with this id couldn't be found!");
}

bool FLUSH_BY_UNIQUE_ID(JPointer pointer)
{
    int target = -1;
    for (int i = 0; i < CACHED_FUTURES.size(); ++i) {
        auto& c = CACHED_FUTURES[i];
        if (c.first == pointer)
        {
            target = i;
            break;
        }
    }
    if (target == -1)
    {
        throw FutureNotFoundException("A Future with this id couldn't be found!");
    } else
    {
        CACHED_FUTURES.erase(CACHED_FUTURES.begin() + target);
        return true;
    }
}

JPointer cast(jint& unique_id)
{
    if (unique_id != -1)
    {
        return static_cast<unsigned int>(unique_id);
    }
    return 0;
}

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    async
 * Signature: (ILorg/cadium/future/FutureTask;)V
 */
JNIEXPORT void JNICALL Java_org_cadium_future_FutureStandardExecutor_async
        (JNIEnv* environment, jclass, jint unique_id, jobject handler)
{
    auto pointer = cast(unique_id);
    auto method_id = environment->GetMethodID(environment->GetObjectClass(handler), "on", "()Ljava/lang/Object;");
    std::future<std::any> future = std::async([&environment, &handler, &method_id]()
            {
                return std::any(environment->CallObjectMethod(handler, method_id));
            });
    CACHED_FUTURES.emplace_back( pointer, &future );
}

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    collect
 * Signature: (I)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_cadium_future_FutureStandardExecutor_collect
        (JNIEnv *, jclass, jint unique_id)
{
    auto pointer = cast(unique_id);
    std::future<std::any>* result = GET_BY_UNIQUE(pointer);
    return std::any_cast<jobject>(result->get());
}

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    flush
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_cadium_future_FutureStandardExecutor_flush
        (JNIEnv *, jclass, jint unique_id)
{
    auto pointer = cast(unique_id);
    FLUSH_BY_UNIQUE_ID(pointer);
}

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    wait
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_cadium_future_FutureStandardExecutor_wait
        (JNIEnv *, jclass, jint unique_id)
{
    auto pointer = cast(unique_id);
    std::future<std::any>* result = GET_BY_UNIQUE(pointer);
    result->wait();
}
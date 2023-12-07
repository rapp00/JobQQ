import redis.clients.jedis.Jedis;

public class JobQueueExample {

    public static void main(String[] args) {
        // Ganti nilai host, port, dan password sesuai konfigurasi Redis Anda
        String redisHost = "Redis Server";
        int redisPort = 6379;
        String redisPassword = "your_password"; // Ganti dengan password Redis Anda jika diperlukan

        Jedis jedis = new Jedis(redisHost, redisPort);

        // Jika Redis server Anda memerlukan otentikasi, tambahkan baris berikut
        // jedis.auth(redisPassword);

        String queueKey = "myJobQueue";

        // Enqueue jobs
        enqueueJob(jedis, queueKey, "Job 1");
        enqueueJob(jedis, queueKey, "Job 2");
        enqueueJob(jedis, queueKey, "Job 3");

        // Dequeue jobs (FIFO)
        System.out.println("Dequeued job: " + dequeueJob(jedis, queueKey));
        System.out.println("Dequeued job: " + dequeueJob(jedis, queueKey));
        System.out.println("Dequeued job: " + dequeueJob(jedis, queueKey));

        jedis.close();
    }

    private static void enqueueJob(Jedis jedis, String queueKey, String job) {
        jedis.rpush(queueKey, job);
    }

    private static String dequeueJob(Jedis jedis, String queueKey) {
        return jedis.lpop(queueKey);
    }
}
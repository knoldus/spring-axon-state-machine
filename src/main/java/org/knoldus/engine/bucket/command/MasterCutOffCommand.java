package engine.bucket.command;


import engine.bucket.dto.BucketMasterSyn;

public class MasterCutOffCommand extends BaseCommand<String>{


    private final BucketMasterSyn bucketMasterSyn;
    public MasterCutOffCommand(String id, BucketMasterSyn bucketMasterSyn) {
        super(id);
        this.bucketMasterSyn = bucketMasterSyn;
    }

    public BucketMasterSyn getBucketMasterSyn() {
        return bucketMasterSyn;
    }
}
